package com.jin10.spidermanage.dto;

import com.jin10.spidermanage.entity.Label;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDTO implements Serializable {

    private Integer id;

    private String category;

    private List<LabelDTO> labels;

}
