package com.jin10.spidermanage.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.label.InsertBody;
import com.jin10.spidermanage.entity.ImgUrl;
import com.jin10.spidermanage.entity.Label;
import com.jin10.spidermanage.service.CategoryService;
import com.jin10.spidermanage.service.ImgUrlService;
import com.jin10.spidermanage.service.LabelService;
import com.jin10.spidermanage.service.LinkService;
import com.jin10.spidermanage.util.Http;
import com.jin10.spidermanage.util.HttpClientUtils;
import com.jin10.spidermanage.util.RegularUtil;
import com.jin10.spidermanage.util.XxlJobUtil;
import com.jin10.spidermanage.vo.LabelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/label")
public class LabelController {

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;
    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Autowired
    private LabelService labelService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public BaseResponse getAll() {
        return BaseResponse.ok(labelService.getAll());
    }

    @GetMapping("/get/{lid}")
    public BaseResponse getById(@PathVariable("lid") Integer id) {

        LabelVO template = labelService.getById(id);
        if (null != template) {
            // 查询该模板的所有目录id
            Integer gid = template.getGid();        // 父级目录id
            template.setRid(categoryService.getDirectorys(gid));
        }

        return BaseResponse.ok(template);
    }


    @PostMapping("/add")
    public BaseResponse add(@Validated @RequestBody InsertBody body, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.error("参数校验异常！ ==> {}", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return BaseResponse.error(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
//        return labelService.add(body);
        return labelService.insertElement(body);
    }

    @PostMapping("/update")
    public BaseResponse update(@Valid @RequestBody InsertBody body, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            log.error("参数校验异常！ ==> {}", Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
            return BaseResponse.error(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
//        return labelService.updateLabel(body);
        return labelService.updateElement(body);
    }

    /**
     * 删除调度任务
     *
     * @param lid
     * @param taskId
     * @return
     */
    @GetMapping("/delete")
    public BaseResponse delete(@PathParam("lid") Integer lid, @PathParam("taskId") @Positive Long taskId) {
        if (ObjectUtils.isNotNull(lid) && ObjectUtils.isNotNull(taskId)) {
            return BaseResponse.ok(labelService.delete(lid, taskId));
        }
        return BaseResponse.ok("参数不能为空！！");

    }

    /**
     * 返回执行器列表
     *
     * @return
     */
    @GetMapping("/executorList")
    public BaseResponse executorList() throws IOException {
        return BaseResponse.ok(XxlJobUtil.executorList(adminAddresses).getData());
    }
}
