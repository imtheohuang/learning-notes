package com.theo.template.engine;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author huangsuixin
 * @date 2019/09/20 14:44
 * @description Thymeleaf 模板引擎引导类
 */
public class ThymeleafTemplateEngineBootstrap {
    public static void main(String[] args) throws IOException {
        // 构建引擎
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        // 创建渲染上下文
        Context context = new Context();
        context.setVariable("message", "Hello World");

        // 读取内容从 classpath:/templates/thymeleaf/hello-world.html
        // ResourceLoader
        ResourceLoader resourceloader = new DefaultResourceLoader();
        // 通过 classpath:/templates/thymeleaf/hello-world.html Resource
        Resource resource = resourceloader.getResource("classpath:/templates/thymeleaf/hello-world.html");
        File templateFile = resource.getFile();

        // 文件流
        FileInputStream inputStream = new FileInputStream(templateFile);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Copy
        IOUtils.copy(inputStream, outputStream);


        inputStream.close();


        // 模板的内容
//        String content = "<p th:text=\"${message}\">!!!</p>";
        String content = outputStream.toString("UTF-8");
        // 渲染（处理）结果
        String result = templateEngine.process(content, context);
        // 输出渲染结果
        System.out.println(result);
    }
}
