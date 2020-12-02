package com.jin10.spidermanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jin10.spidermanage.bean.BaseResponse;
import com.jin10.spidermanage.entity.User;
import com.jin10.spidermanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/add")
    public BaseResponse addOrUpdate(@RequestParam(defaultValue = "0", value = "updater_id") Integer updaterId, @RequestParam String name, @RequestParam String iphone) {
        return userService.addOrUpdate(updaterId, name, iphone);
    }

    @GetMapping("/get")
    public BaseResponse getInfo(@RequestParam String name) {
        return BaseResponse.ok(userService.getOne(new QueryWrapper<User>().eq("name", name)));
    }


}
