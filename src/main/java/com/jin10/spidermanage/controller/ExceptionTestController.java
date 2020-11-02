package com.jin10.spidermanage.controller;

import com.jin10.spidermanage.exception.http.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
public class ExceptionTestController {

    @GetMapping
    public void test() {
        throw new NotFoundException(600);
    }
}
