package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.service.LabelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
//@CrossOrigin(origins = {"*","null"},allowCredentials="true")
public class SearchController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public BaseResponse searchLabel(@RequestParam(value = "condition") String condition) {
        if (StringUtils.isNotBlank(condition)) {
            return BaseResponse.ok(labelService.getLabelByCondition(condition));
        }else {
            return BaseResponse.error(401, "搜索条件不能为空");
        }
    }
}
