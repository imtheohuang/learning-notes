package com.theo.sorm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装反射常用操作
 * @author huangsuixin
 * @date 2019/10/21 22:04
 */
public class ReflectUtils {

    /**
     * 调用 get 方法
     * @param
     * @param fieldName
     * @param o
     * @return
     */
    public static Object invokeGetter(String fieldName, Object o) {
        try {
            Class<?> c = o.getClass();
            Method m = c.getMethod("get" + StringUtils.firstCharUpperCase(fieldName), null);
            Object priKeyValue = m.invoke(o, null);
            return priKeyValue;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void invokeSetter(Object o, String columnName, Object columnValue) {
        Method declaredMethod = null;
        try {
            declaredMethod = o.getClass().getDeclaredMethod("set" + StringUtils.firstCharUpperCase(columnName), columnValue.getClass());
            declaredMethod.invoke(o, columnValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
