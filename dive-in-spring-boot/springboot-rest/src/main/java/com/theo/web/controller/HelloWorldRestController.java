package com.theo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsuixin
 * @date 2019/09/25 18:21
 * @description HelloWorld {@link RestController} 实现
 */
@RestController
public class HelloWorldRestController {

    @GetMapping(value = "hello-world")
    public String helloWorld() {

        return "Hello, World!";
    }

}
