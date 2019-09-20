package com.theo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangsuixin
 * @date 2019/09/17 17:16
 * @description //TODO
 */
@Controller
public class HelloWorldController {

    @RequestMapping("")
    public String hello(Model model) {
//        model.addAttribute("acceptLanguage", acceptLanguage);
//        model.addAttribute("jsessionId", jsessionId);
//        model.addAttribute("message", message);

        return "index";
    }



}
