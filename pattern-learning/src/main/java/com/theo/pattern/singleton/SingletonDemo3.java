package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 15:09
 * @description 双重检测锁实现
 */
public class SingletonDemo3 {

    private static SingletonDemo3 instance;

    private SingletonDemo3(){}

    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo3.class) {
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }
}
