package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.entity.Category;
import com.jin10.spidermanage.mapper.CategoryMapper;
import com.jin10.spidermanage.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/group")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public BaseResponse getAll() {
        return BaseResponse.ok(categoryService.getAll());
    }

    @GetMapping("/get/{id}")
    public BaseResponse getById(@PathVariable("id") Integer id) {
        return BaseResponse.ok(categoryService.getById(id));
    }


    @PostMapping("/add")
    public BaseResponse add(@RequestParam("category") String category) {
        return BaseResponse.ok(categoryService.save(new Category().setCategoryName(category)));
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestParam("id") Integer id, @RequestParam("category") String category) {
        return BaseResponse.ok(categoryService.update(id, category));
    }

    @GetMapping("/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) {
        return BaseResponse.ok(categoryService.delete(id));
    }
}
