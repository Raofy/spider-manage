package com.jin10.spidermanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.bean.spider.ExecutorList;
import com.jin10.spidermanage.dto.CategoryDTO;
import com.jin10.spidermanage.dto.CategoryDTO1;
import com.jin10.spidermanage.entity.Category;
import com.jin10.spidermanage.mapper.CategoryMapper;
import com.jin10.spidermanage.service.CategoryService;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;


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
    public CategoryDTO1 getAllTest() {

        try {
            return new CategoryDTO1(categoryMapper.getAll(), XxlJobUtil.executorList(adminAddresses).getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(Integer id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    public CategoryDTO getById(Integer id) {
        return categoryMapper.getById(id);
    }
}
