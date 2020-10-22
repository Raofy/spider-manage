package com.jin10.spidermanage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse spiderTest(InsertBody body) {
        List<String> spiderLink = body.getSpiderLink();
        for (int i = 0; i < spiderLink.size(); i++) {
            String targetUrl = null;
            if (StringUtils.isNotBlank(body.getParam())) {
                targetUrl = String.format("%s?%s", spiderLink.get(i), body.getParam());
            }else {
                targetUrl = spiderLink.get(i);
            }
            BaseResponse request = Http.request(targetUrl);
            if (ObjectUtil.isNotNull(request.getData())) {
                return request;
            }
        }
        return BaseResponse.error("爬取失败！！，请检查路径");
    }

    @PostMapping("/fetch")
    public BaseResponse fetch(InsertBody body) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(body.getExecutorId()));
        List<String> spiderLink = body.getSpiderLink();
        for (int i = 0; i < spiderLink.size(); i++) {
            String targetUrl = String.format("%s?%s", spiderLink.get(i), body.getParam());
            params.put("executorParam", targetUrl);
            JSONObject response = XxlJobUtil.triggerJob(adminAddresses, params);
            if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                return BaseResponse.ok();
            }
        }
        return BaseResponse.error("执行失败！！，请检查路径");
    }

    @GetMapping("/start")
    public BaseResponse start(@PathVariable("lid") Integer id, @PathVariable("open") int open, @PathVariable("executorId") Integer eid) throws IOException {
        if (open == 1) {
            LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Label::getId, id).set(Label::getOpen, open);
            Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
            if (rows == 1 && eid > 0) {
                JSONObject response = XxlJobUtil.startJob(adminAddresses, eid);
                if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                    return BaseResponse.ok("启动成功");
                }
                lambdaUpdateWrapper.eq(Label::getId, id).set(Label::getOpen, 0);
                labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                return BaseResponse.error("调度启动失败");
            }
        }

        return BaseResponse.error("open不为1");
    }

    @GetMapping("/stop")
    public BaseResponse stop(@PathVariable("lid") Integer id, @PathVariable("open") int open, @PathVariable("executorId") Integer eid) throws IOException {
        if (open == 0) {
            LambdaUpdateWrapper<Label> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper.eq(Label::getId, id).set(Label::getOpen, open);
            Integer rows = labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
            if (rows == 1 && eid > 0) {
                JSONObject response = XxlJobUtil.stopJob(adminAddresses, eid);
                if (response.containsKey("code") && 200 == (Integer) response.get("code")) {
                    return BaseResponse.ok("启动成功");
                }
                lambdaUpdateWrapper.eq(Label::getId, id).set(Label::getOpen, 1);
                labelService.getBaseMapper().update(null, lambdaUpdateWrapper);
                return BaseResponse.error("调度启动失败");
            }
        }
        return BaseResponse.error("open不为0");
    }
}
