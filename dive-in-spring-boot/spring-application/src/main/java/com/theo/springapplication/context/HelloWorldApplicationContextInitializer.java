package com.theo.springapplication.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * @author huangsuixin
 * @date 2019/09/16 17:58
 * @description //TODO
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HelloWorldApplicationContextInitializer<C extends ConfigurableApplicationContext>
        implements ApplicationContextInitializer<C> {
    @Override
    public void initialize(C applicationContext) {

        System.out.println("ConfigurableApplicationContext.id = " + applicationContext.getId());
    }
}
