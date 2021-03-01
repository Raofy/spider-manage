package com.jin10.spidermanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jin10.spidermanage.entity.Link;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.mapper.LinkMapper;
import com.jin10.spidermanage.vo.Category;
import com.jin10.spidermanage.vo.CategoryList;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.mapper.CategoryMapper;
import com.jin10.spidermanage.service.CategoryService;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.util.XxlJobUtil;
import com.jin10.spidermanage.vo.LabelVO1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.io.IOException;
import java.util.*;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, com.jin10.spidermanage.entity.Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Autowired
    private LabelService labelService;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private ImgUrlService imgUrlService;

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Override
    public int update(Integer id, String newName, long parentId) {
        return categoryMapper.updateById(new com.jin10.spidermanage.entity.Category().setId(id).setCategoryName(newName).setParentId(parentId));
    }


    @Override
    public CategoryList getAll() {
        try {
            List<Category> all = categoryMapper.getAll();
            List<Category> folderTree = new ArrayList<>();
            for (Category item : all) {
                if (item.getParentId() == 0) {               //根目录
                    Category category = new Category();
                    category.setId(item.getId());
                    category.setLabels(item.getLabels());
                    category.setParentId(item.getParentId());
                    category.setName(item.getName());
                    List<Category> tree = getFolderTree(item.getId(), all);
                    category.getLabels().addAll(tree);
                    category.setChildren(tree);
                    folderTree.add(category);
                }
            }
            return new CategoryList(folderTree, XxlJobUtil.executorList(adminAddresses).getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transient
    public int delete(Integer id) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();
        List ids = new ArrayList();
        ids.add(id);
        categoryMapper.selectList(null).forEach(item -> {
            if (id == item.getParentId()) {
                ids.add(item.getId());
            }
        });
        List<Label> labels = labelService.getBaseMapper().selectList(queryWrapper.select("id").in("category_id", ids));
        ArrayList<Integer> list = new ArrayList<>();
        if (labels.size() > 0) {
            for (Label l : labels) {
                list.add(l.getId());
            }
            imgUrlService.delete(list);
            linkMapper.delete(new QueryWrapper<Link>().in("label_id", list));
            labelMapper.deleteBatchIds(list);
        }
        return categoryMapper.deleteBatchIds(ids);
    }

    @Override
    public Category getById(Integer id) {
        List<Category> all = categoryMapper.getAll();
        Category result = categoryMapper.getById(id);
        result.getLabels().addAll(getFolderTree(id, all));
        return result;
    }

    @Override
    public String getDirectorys(Integer id) {
        List<Category> all = categoryMapper.getAll();
        int flag = id;
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        while (flag != 0) {
            for (Category item : all) {
                if (item.getId() == flag) {
                    if (item.getParentId() > 0) {
                        ids.add((int) item.getParentId());
                    }
                    flag = (int) item.getParentId();
                    break;
                }
            }
        }
        Collections.reverse(ids);
        return StringUtils.join(ids, ",");
    }

    /**
     * 递归获取子目录
     *
     * @param id
     * @param all
     * @return
     */
    public List<Category> getFolderTree(long id, List<Category> all) {
        List<Category> result = new ArrayList<>();
        getChildren(id, all).forEach(item -> {
            Category category = new Category();
            category.setId(item.getId());
            category.setParentId(id);
            category.setName(item.getName());
            category.setLabels(labelMapper.findByCategoryId(item.getId()));
            List<Category> folderTree = getFolderTree(item.getId(), all);
            category.setChildren(folderTree);
            result.add(category);
        });
        return result;
    }


    /**
     * 获取当前组别的id的所有子目录
     *
     * @param id
     * @param all
     * @return
     */
    public List<Category> getChildren(long id, List<Category> all) {
        List<Category> childrenCategories = new ArrayList<>();
        all.forEach(item -> {
            if (item.getParentId() == id) {
                childrenCategories.add(item);
            }
        });
        return childrenCategories;
    }
}
