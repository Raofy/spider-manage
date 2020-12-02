package com.jin10.spidermanage.bean.spider;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class XxlJobResponse {

    private Integer code;

    private String msg;

    private Object content;


    public String toString() {
        return new String("响应状态码为：" + this.code + ";" + "message为：" + this.msg + "; " + "content内容为：" + content + "。");
    }
}
