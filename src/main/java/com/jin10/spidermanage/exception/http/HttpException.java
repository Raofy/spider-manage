package com.jin10.spidermanage.exception.http;

import lombok.Setter;

public class HttpException extends RuntimeException{

    Integer status;

    Integer httpStateCode = 500;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHttpStateCode() {
        return httpStateCode;
    }

    public void setHttpStateCode(Integer httpStateCode) {
        this.httpStateCode = httpStateCode;
    }
}
