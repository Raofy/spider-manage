package com.jin10.spidermanage.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.jin10.spidermanage.util.Http;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DemoJobHandler extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Http http;

    @XxlJob(value = "Job")
    @Override
    public ReturnT<String> execute(String param) {
        logger.info(DateUtil.now() + " 调度任务：" + param + " 调度线程" + Thread.currentThread().getName());
        HttpResponse httpResponse = HttpRequest.get(param).executeAsync();
        logger.info(DateUtil.now() + " 调度任务：" + param + " 调度线程" + Thread.currentThread().getName() + " 处理结果 " + httpResponse.body());
//        http.asyncRequest(param);
        if (httpResponse.getStatus() == 200) {
            return ReturnT.SUCCESS;
        } else {
            return ReturnT.FAIL;
        }
    }
}
