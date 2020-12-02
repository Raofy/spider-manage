package com.jin10.spidermanage.task;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.util.Http;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DemoJobHandler extends IJobHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicInteger counts = new AtomicInteger();

    @XxlJob(value = "Job")
    @Override
    public ReturnT<String> execute(String param) throws Exception {

        /**
         * 请求接口
         */
        //logger.info("请求参数：" + param);
        BaseResponse request = Http.request(param);
        //logger.info(""+request.getData());
        // 打印日志
        //logger.info("[execute][定时第 ({}) 次执行]", counts.incrementAndGet());
        // 返回执行成功
        return new ReturnT(request.toString());


    }
}
