package com.jin10.spidermanage.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class Http {

    @Autowired
    private Help help;

    public static BaseResponse asyncRequest(String url, Map<String, String> params) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String s = HttpClientUtils.doGetRequest(url, header, params);
        if (StringUtils.isNotBlank(s)) {
            return BaseResponse.ok(JSONObject.toJavaObject(JSON.parseObject(s), Request.class).getData());
        }
        return null;
    }

    @Async("asyncMq")
    public  void asyncRequest(String url) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        help.doGetRequest(url, header, null);
    }

    public  BaseResponse request(String url) {
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
