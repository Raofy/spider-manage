package com.jin10.spidermanage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class Http {

    public static BaseResponse request(String url, Map<String, String> params) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String s = HttpClientUtils.doGetRequest(url, header, params);
        if (StringUtils.isNotBlank(s)) {
            return BaseResponse.ok(JSONObject.toJavaObject(JSON.parseObject(s), Request.class).getData());
        }
        return null;
    }

    public static BaseResponse request(String url) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String s = HttpClientUtils.doGetRequest(url, header, null);
        if (StringUtils.isNotBlank(s)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.parseObject(s);
            } catch (Exception e) {
                return new BaseResponse(s);
            }
            return new BaseResponse(JSONObject.toJavaObject(JSON.parseObject(s), Request.class).getData());
        }
        return null;
    }
}
