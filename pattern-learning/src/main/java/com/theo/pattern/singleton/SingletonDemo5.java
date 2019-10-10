package com.theo.pattern.singleton;

/**
 * @author huangsuixin
 * @date 2019/10/08 15:32
 * @description 使用枚举实现单例模式
 * 优点：实现简单；枚举本身就是单例模式，由JVM从根本上提供保障！避免通过反射和反序列化的漏洞！
 * 缺点：无懒加载
 */
public enum SingletonDemo5 {
    INSTANCE;

}
