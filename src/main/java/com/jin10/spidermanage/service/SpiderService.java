package com.jin10.spidermanage.service;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;

import java.io.IOException;

public interface SpiderService {

    BaseResponse test(InsertBody body);

    BaseResponse fetch(InsertBody body) throws IOException;

    BaseResponse powerSwitch(Integer lid, Integer open, Integer taskId) throws IOException;
}
