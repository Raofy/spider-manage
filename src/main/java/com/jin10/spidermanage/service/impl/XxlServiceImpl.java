package com.jin10.spidermanage.service.impl;

import com.alibaba.fastjson.JSON;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.spider.Executor;
import com.jin10.spidermanage.bean.spider.XxlJobResponse;
import com.jin10.spidermanage.service.XxlService;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class XxlServiceImpl implements XxlService {
    @Override
    public BaseResponse saveExecutor(String host, Executor executor) throws IOException {

        Map<String, String> mapData = new HashMap<>();
        mapData.put("id", String.valueOf(executor.getId()));
        mapData.put("appname", executor.getAppname());
        mapData.put("title", executor.getTitle());
        mapData.put("addressType", String.valueOf(0));
        XxlJobResponse xxlJobResponse = XxlJobUtil.addExecutor(host, mapData);
        if (xxlJobResponse.getCode() == 200) {
            return BaseResponse.ok(XxlJobUtil.executorList(host));
        }
        return BaseResponse.error(xxlJobResponse.getMsg());
    }

    @Override
    public BaseResponse updateExecutor(String host, Executor executor) throws IOException {
        Map<String, String> mapData = new HashMap<>();
        mapData.put("id", String.valueOf(executor.getId()));
        mapData.put("appname", executor.getAppname());
        mapData.put("title", executor.getTitle());
        mapData.put("addressType", String.valueOf(0));
        XxlJobResponse xxlJobResponse = XxlJobUtil.updateExecutor(host, mapData);
        if (xxlJobResponse.getCode() == 200) {
            return BaseResponse.ok(XxlJobUtil.executorList(host));
        }
        return BaseResponse.error(xxlJobResponse.getMsg());
    }

    @Override
    public BaseResponse deleteExecutor(String host, Long id) throws IOException {
        XxlJobResponse response = XxlJobUtil.deleteExecutor(host, id);
        if (response.getCode() == 200) {
            return BaseResponse.ok(XxlJobUtil.executorList(host));
        }
        return BaseResponse.error(response.getCode(), response.getMsg());

    }
}
