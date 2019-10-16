package com.tho.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author huangsuixin
 * @date 2019/10/13 22:08
 * @description //TODO
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterServerApplication.class, args);
    }
}
