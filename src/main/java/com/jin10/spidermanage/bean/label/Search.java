package com.jin10.spidermanage.bean.label;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 全局检索返回结果
 */
@Data
public class Search {

    /**
     * 组别ID
     */
    @JsonProperty("gid")
    private Integer categoryId;

    /**
     * 标签ID
     */
    @JsonProperty("lid")
    private Integer id;

    /**
     * 标签名
     */
    @JsonProperty("label_name")
    private String labelName;

}
