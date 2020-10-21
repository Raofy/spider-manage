package com.jin10.spidermanage.bean.spider;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable {
    private int code;
    private String status;
    private Object data;
}
