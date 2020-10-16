package com.jin10.spidermanage.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.io.Serializable;

@Data
public class LabelDTO implements Serializable {

    private Integer id;

    @JsonProperty("label_name")
    private String labelName;
}
