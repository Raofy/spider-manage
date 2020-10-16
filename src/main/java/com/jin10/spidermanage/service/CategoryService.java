package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.dto.CategoryDTO;
import com.jin10.spidermanage.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    int add(String name);

    List<CategoryDTO> getAll();

    int delete(Integer id);

    int update(Integer id, String newName);

    Category getById(Integer id);


}
