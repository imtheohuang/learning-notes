package com.theo.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.File;

/**
 * @author huangsuixin
 * @date 2019/09/20 18:06
 * @description //TODO
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        // ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);

        // 优先级高于 ThymeleafViewResolver
        return viewResolver;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return factory -> {
            factory.addContextCustomizers(context -> {
                String relativePath = "springboot-view/src/main/webapp";
                File docBaseFile = new File(relativePath);
                if (docBaseFile.exists()) {
                    context.setDocBase(docBaseFile.getAbsolutePath());
                }
            });
        };
    }
}
