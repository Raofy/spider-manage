package com.jin10.spidermanage.exception.http;

public class NotFoundException extends HttpException{

    public NotFoundException(Integer status) {
        this.status = status;
        this.httpStateCode = 404;
    }
}
