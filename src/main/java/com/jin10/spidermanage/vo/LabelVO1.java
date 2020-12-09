package com.jin10.spidermanage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelVO1 {

//    private Integer lid;
    private Integer id;

//    private String labelName;
    private String name;

    private List<LabelVO1> labels;

    private List<LabelVO1> children;
}
