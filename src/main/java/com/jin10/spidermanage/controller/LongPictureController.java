package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/picture")
public class LongPictureController {

    @Autowired
    private LabelService labelService;

    /**
     * 长图返回维护者信息
     *
     * @param url
     * @return
     */
    @PostMapping("/getMaintainer")
    public BaseResponse getMaintainer(String url) {
        return labelService.getMaintainerByParams(url);
    }
}
