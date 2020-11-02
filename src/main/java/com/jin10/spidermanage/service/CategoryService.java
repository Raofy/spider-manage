package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.vo.Category;
import com.jin10.spidermanage.vo.CategoryList;

public interface CategoryService extends IService<com.jin10.spidermanage.entity.Category> {


    CategoryList getAll();

    int delete(Integer id);

    int update(Integer id, String newName);

    Category getById(Integer id);


}
