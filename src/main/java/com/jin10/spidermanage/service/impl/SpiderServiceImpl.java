package com.jin10.spidermanage.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.entity.Server;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.ServerService;
import com.jin10.spidermanage.service.SpiderService;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.HttpClientUtils;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpiderServiceImpl implements SpiderService {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    @Autowired
    private LabelService labelService;

    @Autowired
    private Http http;

    @Autowired
    private ServerService serverService;

    @Override
    public BaseResponse test(InsertBody body) {
        if (StringUtils.isNotBlank(body.getParam())) {
            QueryWrapper<Server> queryWrapper = new QueryWrapper<>();
            Server server = serverService.getBaseMapper().selectOne(queryWrapper.eq("id", body.getServerId()));
            String url = null;
            if(StringUtils.isNotBlank(server.getPort())) {
                url = String.format("http://%s:%s/test?%s",server.getServerIp(),server.getPort(),body.getParam());
            }else {
                url = String.format("http://%s/test?%s",server.getServerIp(),body.getParam());
            }
            BaseResponse request = http.request(url);
            if (ObjectUtil.isNotNull(request.getData())) {
                return request;
            }
        }
        return BaseResponse.error("爬取失败！！，请检查路径");
    }

    @Override
    public BaseResponse fetch(InsertBody body) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(body.getTaskId()));
        if (StringUtils.isNotBlank(body.getParam())) {
            QueryWrapper<Server> queryWrapper = new QueryWrapper<>();
            Server server = serverService.getBaseMapper().selectOne(queryWrapper.eq("id", body.getServerId()));
            String url = null;
            if(StringUtils.isNotBlank(server.getPort())) {
                url = String.format("http://%s:%s/fetch?%s",server.getServerIp(),server.getPort(),body.getParam());
            }else {
                url = String.format("http://%s/fetch?%s",server.getServerIp(),body.getParam());
            }
            params.put("executorParam", url);
            XxlJobResponse xxlJobResponse = XxlJobUtil.triggerJob(adminAddresses, params);
            if (xxlJobResponse.getCode() == 200) {
                return BaseResponse.ok();
            }
        }

        return BaseResponse.error("执行失败！！，请检查路径");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse powerSwitch(Integer lid, Integer open, Integer taskId) throws IOException {
        if (ObjectUtil.isNotNull(lid) && ObjectUtil.isNotNull(open) && ObjectUtil.isNotNull(taskId)) {
            LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            if (taskId > 0) {
                if (open == 1) {
                    XxlJobResponse response = XxlJobUtil.startJob(adminAddresses, taskId);
                    if (response.getCode() == 200) {
                        lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 0);
                        Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                        return BaseResponse.ok(labelService.getById(lid));
                    }
                    lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 1);
                    labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                } else {
                    XxlJobResponse response = XxlJobUtil.stopJob(adminAddresses, taskId);
                    if (response.getCode() == 200) {
                        lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 1);
                        Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                        return BaseResponse.ok(labelService.getById(lid));
                    }
                    lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 0);
                    labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                }
                return BaseResponse.error("调度启动失败");
            }
        }
        return BaseResponse.error("参数不能为空！！");
    }



}
