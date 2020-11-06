package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.bean.server.OperationBody;
import com.jin10.spidermanage.entity.Server;
import com.jin10.spidermanage.service.ServerService;
import com.jin10.spidermanage.vo.ServerVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/server")
@ResponseBody
public class ServerController {

    @Autowired
    private ServerService serverService;

    @PostMapping("/add")
    public BaseResponse addServer(@Validated @RequestBody OperationBody body, BindingResult result) {
        if (result.hasErrors()) {
            return BaseResponse.error(400, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return serverService.addElement(body);
    }

    @GetMapping("/get/{server_id}")
    public BaseResponse getById(@PathVariable("server_id") Integer id) {
        return BaseResponse.ok(new ServerVO(serverService.getById(id)));
    }

    @GetMapping("/get/list")
    public BaseResponse getList() {
        return BaseResponse.ok(serverService.getList());
    }

    @GetMapping("/get/all")
    public BaseResponse getAll(@RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "current_page", defaultValue = "1")Integer currentPage) {
        return serverService.getAll(pageSize, currentPage);
    }

    @PostMapping("/update")
    public BaseResponse update(@Validated @RequestBody OperationBody body, BindingResult result) {
        if (result.hasErrors()) {
            return BaseResponse.error(400, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        }
        return BaseResponse.ok(serverService.updateById(new Server(body)));
    }

    @GetMapping("/delete/{server_id}")
    public BaseResponse deleteById(@PathVariable("server_id") Integer id) {
        return BaseResponse.ok(serverService.deleteById(id));
    }

    @GetMapping("/query/")
    public BaseResponse fuzzyQuery(@RequestParam String condition, @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize, @RequestParam(value = "page_num", defaultValue = "1") Integer currentPage) {
        return serverService.fuzzyQuery(condition, pageSize, currentPage);
    }
}
