package com.jin10.spidermanage.task;

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
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DemoJobHandler extends IJobHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger counts = new AtomicInteger();

    @Autowired
    private Http http;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @XxlJob(value = "Job")
    @Override
    public ReturnT<String> execute(String param) throws Exception {

        /**
         * 请求接口
         */
        logger.info("调度线程" + Thread.currentThread().getName() + "执行调度任务：" + param);
        logger.info("当前线程池活动线程的数量：" + threadPoolTaskExecutor.getActiveCount());
        http.asyncRequest(param);
        return ReturnT.SUCCESS;


    }
}
