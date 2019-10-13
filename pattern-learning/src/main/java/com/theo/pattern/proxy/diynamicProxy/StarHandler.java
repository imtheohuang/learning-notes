package com.theo.pattern.proxy.diynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author huangsuixin
 * @date 2019/10/10 15:58
 * @description //TODO
 */
public class StarHandler implements InvocationHandler {
    Star realStar;

    public StarHandler(Star realStar) {
        this.realStar = realStar;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("#####");
        method.invoke(realStar, args);
        return null;

    }
}
