package com.theo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author huangsuixin
 * @date 2019/09/13 11:38
 * @description ServletComponentScan 扫描注册servlet
 */
@SpringBootApplication
//@ServletComponentScan(basePackages = "com.theo.springboot.web.servlet")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
