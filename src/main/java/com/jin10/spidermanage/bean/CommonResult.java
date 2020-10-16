package com.jin10.spidermanage.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jin10.spidermanage.enums.ResponseEnums;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

@Data
public class CommonResult<T> implements Serializable {

    public static Integer CODE_SUCCESS = 200;

    /**
     * 错误码
     */
    private Integer status;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public CommonResult(){

    }
    public CommonResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
    public static CommonResult error(String message) {
        CommonResult baseResponse = new CommonResult();
        baseResponse.status = ResponseEnums.DEFAULT_ERROR.getStatus();
        baseResponse.message = message;
        return baseResponse;
    }

    public CommonResult(ResponseEnums responseEnums) {
        this(responseEnums.getStatus(), responseEnums.getMessage());
    }

    public CommonResult(ResponseEnums responseEnums, T data) {
        this(responseEnums.getStatus(), responseEnums.getMessage(), data);
    }

    public static CommonResult ok() {
        return new CommonResult(ResponseEnums.SUCCESS);
    }

    public static CommonResult ok(Object data) {
        return new CommonResult(ResponseEnums.SUCCESS, data);
    }

    public static CommonResult error() {
        return new CommonResult(ResponseEnums.DEFAULT_ERROR);
    }


    public static CommonResult error(ResponseEnums resultCodeEnum) {
        return new CommonResult(resultCodeEnum.getStatus(), resultCodeEnum.getMessage());
    }


    public static CommonResult error(int errorCode, String message) {
        return new CommonResult(errorCode, message);
    }


    public static CommonResult error(Object data) {
        return new CommonResult(ResponseEnums.DEFAULT_ERROR, data);
    }
    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     *
     * 因为 A 方法返回的 CommonResult 对象，不满足调用其的 B 方法的返回，所以需要进行转换。
     *
     * @param result 传入的 result 对象
     * @param <T> 返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getStatus(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!CODE_SUCCESS.equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.status = code;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.status = CODE_SUCCESS;
        result.message = "请求成功";
        result.data = data;
        return result;
    }

    @JsonIgnore // 忽略，避免 jackson 序列化给前端
    public boolean isSuccess() { // 方便判断是否成功
        return CODE_SUCCESS.equals(status);
    }

    @JsonIgnore // 忽略，避免 jackson 序列化给前端
    public boolean isError() { // 方便判断是否失败
        return !isSuccess();
    }

}
