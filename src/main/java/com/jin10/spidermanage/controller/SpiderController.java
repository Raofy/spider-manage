package com.jin10.spidermanage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
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

    @Autowired
    private SpiderService spiderService;

    @PostMapping("/test")
    public BaseResponse spiderTest(@RequestBody InsertBody body) {
        return spiderService.test(body);
    }

    @PostMapping("/fetch")
    public BaseResponse fetch(@RequestBody InsertBody body) throws IOException {
        return spiderService.fetch(body);
    }

    @GetMapping("/switch")
    public BaseResponse startOrOff(@PathParam("lid") Integer lid, @PathParam("open") Integer open, @PathParam("taskId") Integer taskId) throws IOException {
        return spiderService.powerSwitch(lid, open, taskId);
    }
}
