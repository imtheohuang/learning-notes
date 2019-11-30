package com.theo.spring.boot.web.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author huangsuixin
 * @date 2019/09/27 20:52
 * @description Spring Boot Servlet 引导类
 */
@EnableAutoConfiguration
@ServletComponentScan(basePackages = "com.theo.spring.web.servlet")
public class SpringBootServletBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletBootstrap.class, args);
    }
}
