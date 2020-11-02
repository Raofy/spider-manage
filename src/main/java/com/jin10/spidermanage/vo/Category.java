package com.jin10.spidermanage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {


    private Integer gid;

    private String category;

    private List<LabelVO> labels;


}
