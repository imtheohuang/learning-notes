package com.theo.web.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangsuixin
 * @date 2019/09/25 18:19
 * @description //TODO
 */
@SpringBootApplication(scanBasePackages = {"com.theo.web.controller", "com.theo.web.config"})
public class SpringBootTestBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestBootstrap.class, args);
    }
}
