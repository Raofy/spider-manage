package com.jin10.spidermanage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnums {
    SUCCESS(200, "访问成功！"),

    DUPLICATEKEY_EXCEPTION(300, "数据库中已存在该记录"),

    UNAUTHORIZED(401, "JWT认证失败"),

    ACCESSDENIED(403, "权限不足,无法访问系统资源"),

    NOT_FOUND(404, "路径不存在，请检查路径是否正确"),

    DEFAULT_ERROR(500, "访问错误"),

    //标签类
    ILLEGAL_LABEL(412,"不合法标签"),
    ILLEGAL_EXIT(413, "标签已存在"),



    //ES服务
    ES_NULL(600, "查询结果为空"),

    //请求参数
    PARAM_NULL(700, "请求参数为空,请检查参数"),


    //网关
    GATEWAY_PARAM(800,"网关请求参数错误")

    ;

    private Integer status;
    private String message;
}
