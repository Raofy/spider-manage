package com.jin10.spidermanage.config;

import cn.hutool.core.util.ObjectUtil;
import com.jin10.spidermanage.bean.spider.ExecutorList;
import com.jin10.spidermanage.util.XxlJobUtil;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class XxlJobConfig {
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String appName;
    @Value("${xxl.job.executor.ip}")
    private String ip;
    @Value("${xxl.job.executor.port}")
    private int port;
    @Value("${xxl.job.accessToken}")
    private String accessToken;
    @Value("${xxl.job.executor.logpath}")
    private String logPath;
    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        // 创建 XxlJobSpringExecutor 执行器
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        // 返回
        return xxlJobSpringExecutor;
    }

    @Bean
    public void jobExecutorSave() throws IOException {
        HashMap<String, String> login = new HashMap<>();
        login.put("userName", "admin");
        login.put("password", "123456");
        String admin = XxlJobUtil.login(adminAddresses, "admin", "123456");
        ExecutorList executorList = XxlJobUtil.executorList(adminAddresses);
        if (ObjectUtil.isNotNull(executorList)) {
            List<ExecutorList.DataBean> data = executorList.getData();
            int exit = 0;
            for (int i = 0; i < data.size(); i++) {
                if (appName.equals(data.get(i).getAppname())) {
                    exit = 1;
                }
            }
            if (exit == 0) {
                Map<String, String> params = new HashMap<>();
                params.put("appname", appName);
                params.put("title", "爬虫执行器");
                XxlJobUtil.jobGroupSave(adminAddresses, params);
            }
        }
    }

    @Bean
    public void xxlJobLogin() throws IOException {

    }
}
