package com.theo.pattern.adapter;

/**
 * @author huangsuixin
 * @date 2019/10/10 10:27
 * @description 被适配的对象
 */
public class Adaptee {

    public void doRequest() {
        System.out.println("处理请求");
    }
}
