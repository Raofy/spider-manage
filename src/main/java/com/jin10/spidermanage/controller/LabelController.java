package com.jin10.spidermanage.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.LinkService;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private ImgUrlService imgUrlService;

    @GetMapping("/all")
    public BaseResponse getAll() {
        return BaseResponse.ok(labelService.getAll());
    }

    @GetMapping("/get/{lid}")
    public BaseResponse getById(@PathVariable("lid") Integer id) {
        return BaseResponse.ok(labelService.getById(id));
    }


    @PostMapping("/add")
    public BaseResponse add(@RequestBody InsertBody body) {
        return BaseResponse.ok(labelService.add(body));
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody InsertBody body) {
        return BaseResponse.ok(labelService.updateLabel(body));
    }

    @GetMapping("/delete/{id}")
    public BaseResponse delete(@PathVariable("id") Integer id) {
        return BaseResponse.ok(labelService.delete(id));
    }

}
