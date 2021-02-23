package com.jin10.spidermanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jin10.spidermanage.vo.Category;
import com.jin10.spidermanage.vo.CategoryList;

public interface CategoryService extends IService<com.jin10.spidermanage.entity.Category> {


    CategoryList getAll();

    int delete(Integer id);

    int update(Integer id, String newName, long parentId);

    Category getById(Integer id);

    /**
     * 获取指定组别id的所有父级目录的id
     * @param id
     * @return
     */
    String getDirectorys(Integer id);

}
