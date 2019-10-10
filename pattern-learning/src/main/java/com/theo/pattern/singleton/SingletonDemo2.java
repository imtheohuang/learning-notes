package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 10:48
 * @description 懒汉式
 */
public class SingletonDemo2 {

    private static SingletonDemo2 instance;
    private SingletonDemo2() {}

    public static synchronized SingletonDemo2 getInstance() {
        if (null == instance) {
            instance = new SingletonDemo2();
        }

        return instance;
    }
}
