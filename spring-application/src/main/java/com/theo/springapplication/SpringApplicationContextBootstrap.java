package com.theo.springapplication;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huangsuixin
 * @date 2019/09/17 10:24
 * @description Spring 应用上下文 引导类
 */
public class SpringApplicationContextBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SpringApplicationContextBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        System.out.println("ConfigurableApplicationContext 类型："+ context.getClass().getName());
        System.out.println("Environment 类型："+ context.getEnvironment().getClass().getName());

        context.close();
    }
}
