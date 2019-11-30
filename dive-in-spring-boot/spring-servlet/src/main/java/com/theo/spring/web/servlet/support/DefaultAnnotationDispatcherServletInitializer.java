package com.theo.spring.web.servlet.support;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author huangsuixin
 * @date 2019/09/19 18:18
 * @description Spring Web MVC 自动装配 默认实现
 */
@ComponentScan(basePackages = "com.theo.spring.web.servlet.controller")
@Configuration
public class DefaultAnnotationDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * web.xml
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * DispatcherServlet
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{getClass()};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
