package com.jin10.spidermanage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.RegularUtil;
import com.jin10.spidermanage.util.XxlJobUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/spider")
public class SpiderController {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    @Autowired
    private LabelService labelService;

    @PostMapping("/test")
    public BaseResponse spiderTest(@RequestBody InsertBody body) {
        if (StringUtils.isNotBlank(body.getParam()) && RegularUtil.url(body.getParam())) {
            body.setParam(body.getParam().replaceFirst("fetch", "test"));
            BaseResponse request = Http.request(body.getParam());
            if (ObjectUtil.isNotNull(request.getData())) {
                return request;
            }
        }
        return BaseResponse.error("爬取失败！！，请检查路径");
    }

    @PostMapping("/fetch")
    public BaseResponse fetch(@RequestBody InsertBody body) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(body.getTaskId()));
        if (StringUtils.isNotBlank(body.getParam()) && RegularUtil.url(body.getParam())) {
            params.put("executorParam", body.getParam());
            XxlJobResponse xxlJobResponse = XxlJobUtil.triggerJob(adminAddresses, params);
            if (xxlJobResponse.getCode() == 200) {

                return BaseResponse.ok();
            }
        }

        return BaseResponse.error("执行失败！！，请检查路径");
    }

    @GetMapping("/switch")
    public BaseResponse start(@PathParam("lid") Integer lid, @PathParam("open") Integer open, @PathParam("taskId") Integer taskId) throws IOException {
        if (ObjectUtil.isNotNull(lid) && ObjectUtil.isNotNull(open) && ObjectUtil.isNotNull(taskId)) {
            LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            if (taskId > 0) {
                if (open == 1) {
                    JSONObject response = XxlJobUtil.startJob(adminAddresses, taskId);
                    if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                        lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 0);
                        Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                        return BaseResponse.ok(labelService.getById(lid));
                    }
                    lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 1);
                    labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                } else {
                    JSONObject response = XxlJobUtil.stopJob(adminAddresses, taskId);
                    if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
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
