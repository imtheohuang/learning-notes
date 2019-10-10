package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 15:24
 * @description 静态内部类实现单例
 * 这种方法：线程安全，调用效率高，并且实现了懒加载。
 */
public class SingletonDemo4 {

    private SingletonDemo4(){}

    private static class SingetonClassInstance {
        private static final SingletonDemo4 INSTANCE = new SingletonDemo4();
}

    public static SingletonDemo4 getInstance() {
        return SingetonClassInstance.INSTANCE;
    }
}
