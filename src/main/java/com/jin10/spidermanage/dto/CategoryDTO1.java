package com.jin10.spidermanage.dto;

import com.jin10.spidermanage.bean.spider.ExecutorList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO1 implements Serializable {


    private List<CategoryDTO> group;

    private List executors;

}
