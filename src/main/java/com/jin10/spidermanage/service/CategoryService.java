package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.dto.CategoryDTO;
import com.jin10.spidermanage.dto.CategoryDTO1;
import com.jin10.spidermanage.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    int add(String name);

    List<CategoryDTO> getAll();

    CategoryDTO1 getAllTest();

    int delete(Integer id);

    int update(Integer id, String newName);

    CategoryDTO getById(Integer id);


}
