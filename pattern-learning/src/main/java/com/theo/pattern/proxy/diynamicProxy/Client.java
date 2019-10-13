package com.theo.pattern.proxy.diynamicProxy;

import java.lang.reflect.Proxy;

/**
 * @author huangsuixin
 * @date 2019/10/10 16:01
 * @description //TODO
 */
public class Client {

    public static void main(String[] args) {
        Star realStar = new RealStar();
        StarHandler handler = new StarHandler(realStar);

        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Star.class}, handler);
        proxy.bookTicket();
        proxy.sing();

    }
}
