package com.jin10.spidermanage.util;


import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.HashMap;



@Slf4j
@Component
public class Http {

    @Async("asyncMq")
    public  void asyncRequest(String url) {
        HashMap<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        HttpClientUtils.doGetRequest(url, header, null);
    }

    public BaseResponse request(String url) {
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
            return new BaseResponse(JSONObject.toJavaObject(jsonObject, Request.class).getData());
        }
        return null;
    }

}
