package com.theo.web.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * @author huangsuixin
 * @date 2019/09/26 10:51
 * @description {@link Properties} {@link org.springframework.http.converter.HttpMessageConverter} 实现
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {

    public PropertiesHttpMessageConverter() {
        // 设置支持的 MediaType
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        // 从 请求头 Content-Type 解析编码
        MediaType contentType = outputMessage.getHeaders().getContentType();
        Charset charset = contentType.getCharset();
        // 当 charset 不存在时，使用 UTF-8
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        // 字节输出流 => 字符输出流
        OutputStream outputStream = outputMessage.getBody();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);


        // Properties => String
        properties.store(writer, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        // 从 请求头 Content-Type 解析编码
        HttpHeaders headers = inputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = contentType.getCharset();
        // 当 charset 不存在时，使用 UTF-8
        charset = charset == null ? Charset.forName("UTF-8") : charset;

        // 字节流 => 字符编码
        InputStream inputStream = inputMessage.getBody();
        InputStreamReader reader = new InputStreamReader(inputStream, charset);

        Properties properties = new Properties();
        // 加载字符流为 Properties 对象
        properties.load(reader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
