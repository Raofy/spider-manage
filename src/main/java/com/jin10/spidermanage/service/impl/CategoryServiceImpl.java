package com.jin10.spidermanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.dto.CategoryDTO;
import com.jin10.spidermanage.entity.Category;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.mapper.CategoryMapper;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private LabelMapper labelMapper;


    @Override
    public int add(String name) {
        return categoryMapper.insert(new Category().setCategoryName(name));
    }

    @Override
    public int update(Integer id, String newName) {

        return categoryMapper.updateById(new Category().setId(id).setCategoryName(newName));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return categoryMapper.getAll();
    }

    @Override
    public int delete(Integer id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }
}
