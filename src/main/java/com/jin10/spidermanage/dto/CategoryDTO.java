package com.jin10.spidermanage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jin10.spidermanage.entity.Label;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {


    private Integer gid;

    private String category;

    private List<LabelDTO> labels;

}
