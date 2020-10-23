package com.jin10.spidermanage;

import com.alibaba.fastjson.JSONObject;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

}
