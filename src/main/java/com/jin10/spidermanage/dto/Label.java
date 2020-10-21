package com.jin10.spidermanage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {

    private Integer lid;

    @JsonProperty("label_name")
    private String labelName;
}
