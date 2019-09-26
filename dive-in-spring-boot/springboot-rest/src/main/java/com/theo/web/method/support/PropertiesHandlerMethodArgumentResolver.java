package com.theo.web.method.support;

import com.theo.web.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author huangsuixin
 * @date 2019/09/26 16:22
 * @description {@link java.util.Properties} 类型 {@link org.springframework.web.method.support.HandlerMethodArgumentResolver}
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        // 复用 PropertiesHttpMessageConverter
        PropertiesHttpMessageConverter converter = new PropertiesHttpMessageConverter();

        // Servlet Request API
        HttpServletRequest request = ((ServletWebRequest) webRequest).getRequest();

        HttpInputMessage httpInputMessage = new ServletServerHttpRequest(request);
        return converter.read(null, null, httpInputMessage);
    }
}
