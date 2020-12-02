package com.jin10.spidermanage.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.BaseResponse;

import java.util.HashMap;
import java.util.List;

public class DingTalk {

    private final static String DING_URL = "http://47.110.147.250:8890/api/send_msg";

    public static Object ding(String msg, List<String> members) {
        HashMap postData = new HashMap();
        postData.put("machine", "Bug-Test");
        postData.put("at_members", members);
        postData.put("msg", msg);
        String post = HttpUtil.post(DING_URL, postData);
        JSONObject jsonObject = JSON.parseObject(post);
        int status = (int) jsonObject.get("code");
        Object message = jsonObject.get("msg");
        return message;
    }

}
