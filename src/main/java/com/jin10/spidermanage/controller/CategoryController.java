package com.jin10.spidermanage.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.entity.Category;
import com.jin10.spidermanage.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/group")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public BaseResponse getAll() {

        return BaseResponse.ok(categoryService.getAll());
    }

    @GetMapping("/get/{gid}")
    public BaseResponse getById(@PathVariable("gid") Integer id) {
        return BaseResponse.ok(categoryService.getById(id));
    }


    @GetMapping("/add")
    public BaseResponse add(@RequestParam String category, @RequestParam(required = false, defaultValue = "0") long parentId) {
        return BaseResponse.ok(categoryService.save(new Category().setCategoryName(category).setParentId(parentId)));
    }

    @GetMapping("/update")
    public BaseResponse update(@RequestParam("gid") Integer id, @RequestParam("category") String category, @RequestParam long parentId) {
        return BaseResponse.ok(categoryService.update(id, category, parentId));
    }

    @GetMapping("/delete/{gid}")
    public BaseResponse delete(@PathVariable("gid") Integer gid) {
        if (ObjectUtil.isNotNull(gid)) {
            return BaseResponse.ok(categoryService.delete(gid));
        } else {
            return BaseResponse.error(401, "参数为空");
        }
    }
}
