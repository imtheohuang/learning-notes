package com.theo.web.config;

import com.theo.web.http.converter.properties.PropertiesHttpMessageConverter;
import com.theo.web.method.support.PropertiesHandlerMethodArgumentResolver;
import com.theo.web.method.support.PropertiesHandlerMethodReturnValueHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangsuixin
 * @date 2019/09/26 11:07
 * @description {@link WebMvcConfigurer} 实现
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        // 获取当前 RequestMappingHandlerAdapter 所有的 Resolver 对象
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
        newArgumentResolvers.add(new PropertiesHandlerMethodArgumentResolver());
        newArgumentResolvers.addAll(argumentResolvers);

        requestMappingHandlerAdapter.setArgumentResolvers(newArgumentResolvers);

        // 获取当前所有的 HandlerMethodReturnValueHandler 对象
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        newReturnValueHandlers.addAll(returnValueHandlers);
        newReturnValueHandlers.add(new PropertiesHandlerMethodReturnValueHandler());

        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 不建议添加到末尾 添加到首位
        converters.add(0, new PropertiesHttpMessageConverter());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

        // 添加自定义 HandlerMethodArgumentResolver 优先级低于内建 HandlerMethodArgumentResolver
//        resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
    }
}
