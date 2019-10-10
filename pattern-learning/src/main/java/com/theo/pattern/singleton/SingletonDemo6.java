package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 10:34
 * @description 饿汉式 解决 反射和反序列化漏洞
 */
public class SingletonDemo6 {

    /**
     * 类初始化时，立即加载
     * 由于加载类时，天然的线程安全的
     */
    private static SingletonDemo6 instance = new SingletonDemo6();

    /**
     * 私有化构造器
     */
    private SingletonDemo6() {
        if (instance != null) {
            // 防止反射生成新对象的漏洞
            throw new RuntimeException("不允许手动创建新对象");
        }
    }

    /**
     * 方法没有同步，调用效率高
     */
    public SingletonDemo6 getInstance() {
        return instance;
    }

    /**
     * 防止放序列化生成新对象的漏洞
     */
    public Object readResolve() {
        return instance;
    }
}
