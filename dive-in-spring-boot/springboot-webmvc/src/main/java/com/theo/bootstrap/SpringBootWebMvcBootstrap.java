package com.theo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangsuixin
 * @date 2019/09/19 19:25
 * @description Spring Boot Web MVC 引导类
 */
@SpringBootApplication(scanBasePackages = "com.theo")
public class SpringBootWebMvcBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebMvcBootstrap.class, args);

    }
}
