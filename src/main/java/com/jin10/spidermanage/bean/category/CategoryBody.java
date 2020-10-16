package com.jin10.spidermanage.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryBody {

    private String category;

    @JsonProperty("label_name")
    private String labelName;
}
