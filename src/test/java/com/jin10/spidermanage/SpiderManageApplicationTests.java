package com.jin10.spidermanage;

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
import java.util.*;

@SpringBootTest
class SpiderManageApplicationTests {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String executorAppname;

    @Test
    void contextLoads() throws IOException, InterruptedException {
//        JSONObject jsonObject = XxlJobUtil.deleteJob(adminAddresses, 15);
//        System.out.println(jsonObject);

//        Http.requestTest("http://baidu.com/");


        XxlJobUtil.executorList(adminAddresses);
    }

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
}
