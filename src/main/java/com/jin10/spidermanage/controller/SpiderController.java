package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.util.Http;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/spider")
public class SpiderController {

    @PostMapping("/test")
    public BaseResponse spiderTest() {
        Map<String, String> params = new HashMap<>();
        params.put("spider", "jiachunwang.latest");
        params.put("msg", "山东");
        return BaseResponse.ok(Http.request("http://192.168.13.175:18888/test", params).getData());
    }

    @PostMapping("/fetch")
    public BaseResponse fetch() {
        Map<String, String> params = new HashMap<>();
        params.put("spider", "jiachunwang.latest");
        params.put("msg", "山东");
        return BaseResponse.ok(Http.request("http://192.168.13.175:18888/fetch", params).getData());
    }
}
