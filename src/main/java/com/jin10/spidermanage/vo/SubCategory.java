package com.jin10.spidermanage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 子级目录
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory implements Serializable {

    private Integer gid;

    private String category;

    private List<SubCategory> children;
}
