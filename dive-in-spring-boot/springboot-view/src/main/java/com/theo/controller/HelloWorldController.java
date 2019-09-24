package com.theo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangsuixin
 * @date 2019/09/20 16:07
 * @description
 */
@Controller
public class HelloWorldController {

    @RequestMapping("hello-world")
    public String helloWorld() {
        return "hello-world";
    }

    @RequestMapping("")
    public String hello(Model model) {
//        model.addAttribute("acceptLanguage", acceptLanguage);
//        model.addAttribute("jsessionId", jsessionId);
//        model.addAttribute("message", message);

        return "index";
    }

}
