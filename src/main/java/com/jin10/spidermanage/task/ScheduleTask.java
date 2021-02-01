package com.jin10.spidermanage.task;

import cn.hutool.core.date.DateUtil;
import com.jin10.spidermanage.util.XxlJobUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ScheduleTask {
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    /**
     * 每个月的最后一天去清除xxl-job上个月的调度日志
     *
     * @throws IOException
     */
    @Scheduled(cron = "0 59 23 28-31 * ?")
    public void clearXxlJobLog() throws IOException {
        log.info(DateUtil.now() + "清理xxl-job日志");
        XxlJobUtil.clearXxlJobLog(adminAddresses, 0,0,1);
    }

    /**
     * 每间隔10分钟请求一次xxl-job，检测是否响应
     *
     * @throws IOException
     */
    @Scheduled(cron = "0 0/5 * * * ?")
//    @Scheduled(cron = "0/15 * * * * ?")
    public void testXxlJobResponse() {
        XxlJobUtil.testXxlJobResponse(adminAddresses);
    }
}
