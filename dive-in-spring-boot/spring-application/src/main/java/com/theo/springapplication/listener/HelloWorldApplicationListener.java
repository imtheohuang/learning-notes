package com.theo.springapplication.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * HelloWorld {@link ApplicationListener} 监听 {@link ContextRefreshedEvent} 事件
 * @author huangsuixin
 * @date 2019/09/16 19:58
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("HelloWorld : " + event.getApplicationContext().getId() + ", timestamp: " + event.getTimestamp());
    }
}
