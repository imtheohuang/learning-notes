package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 10:34
 * @description 饿汉式
 */
public class SingletonDemo1 {

    /**
     * 类初始化时，立即加载
     * 由于加载类时，天然的线程安全的
     */
    private static SingletonDemo1 instance = new SingletonDemo1();

    /**
     * 私有化构造器
     */
    private SingletonDemo1() {

    }

    /**
     * 方法没有同步，调用效率高
     */
    public SingletonDemo1 getInstance() {
        return instance;
    }
}
