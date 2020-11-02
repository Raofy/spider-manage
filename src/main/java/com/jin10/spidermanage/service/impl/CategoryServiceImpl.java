package com.jin10.spidermanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.vo.Category;
import com.jin10.spidermanage.vo.CategoryList;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.mapper.CategoryMapper;
import com.jin10.spidermanage.service.CategoryService;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.util.XxlJobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, com.jin10.spidermanage.entity.Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private ImgUrlService imgUrlService;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Override
    public int update(Integer id, String newName) {
        return categoryMapper.updateById(new com.jin10.spidermanage.entity.Category().setId(id).setCategoryName(newName));
    }


    @Override
    public CategoryList getAll() {
        try {
            return new CategoryList(categoryMapper.getAll(), XxlJobUtil.executorList(adminAddresses).getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transient
    public int delete(Integer id) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        List<Label> labels = labelService.getBaseMapper().selectList(queryWrapper.select("id").eq("category_id", id));
        ArrayList<Integer> list = new ArrayList<>();
        if (labels.size() > 0) {
            for (Label l: labels) {
                list.add(l.getId());
            }
            imgUrlService.delete(list);
        }
        return categoryMapper.deleteById(id);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }
}
