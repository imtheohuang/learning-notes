package com.theo.springcloud.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author huangsuixin
 * @date 2019/10/13 22:18
 * @description //TODO
 */
@EnableEurekaClient
@SpringBootApplication
public class DemoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceApplication.class, args);
    }
}
