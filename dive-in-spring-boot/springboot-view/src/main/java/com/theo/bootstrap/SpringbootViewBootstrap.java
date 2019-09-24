package com.theo.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangsuixin
 * @date 2019/09/20 16:10
 * @description //TODO
 */
@SpringBootApplication(scanBasePackages = "com.theo")
public class SpringbootViewBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootViewBootstrap.class, args);
    }
}
