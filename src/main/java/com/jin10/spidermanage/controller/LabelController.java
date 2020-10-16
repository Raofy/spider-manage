package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.mapper.LabelMapper;
import com.jin10.spidermanage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/all")
    public BaseResponse getAll() {
        return BaseResponse.ok(labelService.getAll());
    }

    @GetMapping("/get/{id}")
    public BaseResponse getById(@PathVariable("id") Integer id) {
        return BaseResponse.ok(labelService.getById(id));
    }


    @PostMapping("/add")
    public BaseResponse add(@RequestParam("category_id") Integer categoryId, @RequestParam("label_name") String labelName ) {

        return BaseResponse.ok(labelService.add(categoryId, labelName));
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestParam("label_id") Integer id, @RequestParam("label_name") String label) {
        return BaseResponse.ok(labelService.update(id, label));
    }

    @GetMapping("/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) {
        return BaseResponse.ok(labelService.delete(id));
    }
}
