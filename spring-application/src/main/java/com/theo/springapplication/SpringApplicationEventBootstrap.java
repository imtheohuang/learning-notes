package com.theo.springapplication;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 应用事件引导
 *
 * @author huangsuixin
 * @date 2019/09/16 21:04
 */
public class SpringApplicationEventBootstrap {
    public static void main(String[] args) {

        // 创建上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册应用事件监听器
        context.addApplicationListener(event -> {
            System.out.println("监听到事件: " + event);
        });

        // 启动上下文
        context.refresh();

        context.publishEvent("Hello World!");
        context.publishEvent("2019");
        context.publishEvent(new ApplicationEvent("Silence") {
        });

        // 关闭上下文
        context.close();
    }
}
