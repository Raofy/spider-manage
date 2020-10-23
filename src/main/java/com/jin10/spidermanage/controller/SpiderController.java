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
            XxlJobResponse xxlJobResponse = XxlJobUtil.triggerJob(adminAddresses, params);
            if (xxlJobResponse.getCode() == 200) {
                return BaseResponse.ok();
            }
        }

        return BaseResponse.error("执行失败！！，请检查路径");
    }

    @GetMapping("/start")
    public BaseResponse start(@PathParam("lid") Integer lid, @PathParam("open") Integer open, @PathParam("taskId") Integer taskId) throws IOException {
        if (ObjectUtil.isNotNull(lid) && ObjectUtil.isNotNull(open) && ObjectUtil.isNotNull(taskId)) {
            if (open == 1) {
                LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, open);
                Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                if (rows == 1 && taskId > 0) {
                    JSONObject response = XxlJobUtil.startJob(adminAddresses, taskId);
                    if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                        return BaseResponse.ok("启动成功");
                    }
                    lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 0);
                    labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                    return BaseResponse.error("调度启动失败");
                }
            }

            return BaseResponse.error("open不为1");
        }
        return BaseResponse.error("参数不能为空！！");
    }

    @GetMapping("/stop")
    public BaseResponse stop(@PathParam("lid") Integer lid, @PathParam("open") Integer open, @PathParam("taskId") Integer taskId) throws IOException {
        if (ObjectUtil.isNotNull(lid) && ObjectUtil.isNotNull(open) && ObjectUtil.isNotNull(taskId)) {
            if (open == 0) {
                LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
                lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, open);
                Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                if (rows == 1 && taskId > 0) {
                    JSONObject response = XxlJobUtil.stopJob(adminAddresses, taskId);
                    if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                        return BaseResponse.ok("暂停成功");
                    }
                    lambdaUpdateWrapper.eq(Label::getId, lid).set(Label::getOpen, 1);
                    labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                    return BaseResponse.error("调度启动失败");
                }
            }

            return BaseResponse.error("open不为0");
        }
        return BaseResponse.error("参数不能为空！！");
    }
}
