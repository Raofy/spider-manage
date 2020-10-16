package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.dto.CategoryDTO;
import com.jin10.spidermanage.entity.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryDTO> getAll();

}
