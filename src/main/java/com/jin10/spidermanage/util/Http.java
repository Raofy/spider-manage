package com.jin10.spidermanage.util;

import cn.hutool.core.text.UnicodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.CommonResult;
import com.jin10.spidermanage.bean.spider.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
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
            return BaseResponse.ok(JSONObject.toJavaObject(JSON.parseObject(s), Request.class).getData());
        }
        return null;
    }


    public static void requestTest(String url) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        String s = HttpClientUtils.doGetRequest(url, header, null);
        if (StringUtils.isNotBlank(s)) {
            System.out.println(s);
        }
    }
}
