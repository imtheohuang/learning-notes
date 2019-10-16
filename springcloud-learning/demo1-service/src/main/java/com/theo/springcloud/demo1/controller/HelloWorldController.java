package com.theo.springcloud.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsuixin
 * @date 2019/10/13 22:23
 * @description //TODO
 */
@RestController
@RequestMapping
public class HelloWorldController {
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }
}
