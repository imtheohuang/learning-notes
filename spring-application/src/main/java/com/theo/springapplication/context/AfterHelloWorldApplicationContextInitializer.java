package com.theo.springapplication.context;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * After HelloWorldApplicationContextInitializer
 *
 * @author huangsuixin
 * @date 2019/09/16 17:58
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AfterHelloWorldApplicationContextInitializer
        implements ApplicationContextInitializer, Ordered {

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("After application.id = " + applicationContext.getId());

    }
}
