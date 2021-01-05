package com.jin10.spidermanage;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.bean.dingtalk.DingTalkWarn;
import com.jin10.spidermanage.controller.DingController;
import com.jin10.spidermanage.util.DingTalk;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.*;

@SpringBootTest
class SpiderManageApplicationTests {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    /**
     * 测试钉钉报警接口
     */
    @Autowired
    private DingController controller;
    @Test
    void dingTalkTest() {
        DingTalkWarn dingTalkWarn = new DingTalkWarn();
        dingTalkWarn.setId(18);
        dingTalkWarn.setMsg("测试报警");
        controller.dingTalkWarning(dingTalkWarn);
    }

    /**
     * 测试登录xxl-job，并保存cookie
     */
    @Test
    void testLoginXxl() throws IOException {

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("userName", "admin");
        objectObjectHashMap.put("password", "123456");
        HttpResponse admin = HttpRequest.post(adminAddresses+"/login").form(objectObjectHashMap).execute();
        Iterable<HttpCookie> cookies = admin.getCookies();
        HttpCookie xxl_job_login_identity = admin.getCookie("XXL_JOB_LOGIN_IDENTITY");
        System.out.println();
    }
}
