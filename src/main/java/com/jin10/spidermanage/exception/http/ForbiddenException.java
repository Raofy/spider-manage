package com.jin10.spidermanage.exception.http;

public class ForbiddenException extends HttpException{

    public ForbiddenException(Integer status) {
        this.status = status;
        this.httpStateCode = 403;
    }
}
