package com.theo.sorm.core;

import java.util.List;

/**
 * 负责查询，对外提供服务的核心类
 *
 * @author huangsuixin
 * @date 2019/10/21 21:47
 */
public interface Query {

    /**
     * 执行一个 DML 语句
     *
     * @param sql    sql 语句
     * @param params 参数
     * @return 执行 sql 语句后影响的行数
     */
    int executeDml(String sql, Object[] params);

    /**
     * 将一个对象存储到数据库中
     * 把对象中不为 null 的属性存储到数据库中，如果数字为 null 则放 0
     * 目前只能处理数据库维护主键自增的情况
     * @param object 要储存的对象
     */
    void insert(Object object);

    /**
     * 删除 clazz 表示类对应的表中的记录 （指定主键值id的记录）
     * @param clazz 跟表对应的类的 Class 对象
     * @param id 主键的值
     */
    void delete(Class clazz, Object id);

    /**
     * 删除对象在数据库中对应的记录 （对象所在的类对应到表，对象的主键值对应到记录）
     * @param object
     */
    public void delete(Object object);

    /**
     * 更新对象对应的记录，并且只更新指定的字段的值
     * @param object 所更新的对象
     * @param fieldNames 更新的属性列表
     * @return 影响的行数
     */
    int update(Object object, String[] fieldNames);

    /**
     * 查询返回多行记录，并且每行记录封装到clazz指定的对象中
     * @param sql sql 语句
     * @param clazz
     * @param params
     * @return
     */
    List queryRows(String sql, Class clazz, Object[] params);

    /**
     * 查询返回一行记录
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Object queryUniqueRow(String sql, Class clazz, Object[] params);

    /**
     * 查询返回一个值
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Object queryValue(String sql, Class clazz, Object[] params);

    /**
     * 查询返回一个数字
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Number queryNumber(String sql, Class clazz, Object[] params);

}
