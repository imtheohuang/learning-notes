package com.theo.sorm.core;

/**
 * @author huangsuixin
 * @date 2019/10/21 21:58
 */
public class QueryFactory {

    /**
     * 原型对象
     */
    private static Query prototypeObj;

    static {
        try {
            // 加载指定的 Query 类
            Class<?> aClass = Class.forName(DBManager.getConf().getQueryClass());
            prototypeObj = (Query) aClass.newInstance();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private QueryFactory() {
    }


    public static Query createQuery() {
        try {
            return (Query) prototypeObj.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
