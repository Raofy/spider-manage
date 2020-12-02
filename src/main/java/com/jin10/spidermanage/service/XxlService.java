package com.jin10.spidermanage.service;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Executor;

import java.io.IOException;

public interface XxlService {

    BaseResponse saveExecutor(String host, Executor executor) throws IOException;

    BaseResponse updateExecutor(String host, Executor executor) throws IOException;

    BaseResponse deleteExecutor(String host, Long id) throws  IOException;

}
