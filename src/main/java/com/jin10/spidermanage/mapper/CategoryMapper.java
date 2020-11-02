package com.jin10.spidermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin10.spidermanage.vo.Category;

import java.util.List;

public interface CategoryMapper extends BaseMapper<com.jin10.spidermanage.entity.Category> {

    List<Category> getAll();

    Category getById(Integer id);

}
