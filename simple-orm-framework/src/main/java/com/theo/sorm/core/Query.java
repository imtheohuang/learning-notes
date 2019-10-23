package com.theo.sorm.core;

import com.theo.sorm.bean.ColumnInfo;
import com.theo.sorm.bean.TableInfo;
import com.theo.sorm.utils.JdbcUtils;
import com.theo.sorm.utils.ReflectUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责查询，对外提供服务的核心类
 *
 * @author huangsuixin
 * @date 2019/10/21 21:47
 */
public abstract class Query implements Cloneable{

    /**
     * 采用模板方法模式，将JDBC 操作封装成模板，便于重用
     * @param sql sql
     * @param params sql 的参数
     * @param clazz 要封装到的bean
     * @param callBack 回调方法
     * @return
     */
    public Object executeQueryTemplate(String sql, Object[] params, Class clazz, CallBack callBack) {
        Connection conn = DBManager.getConn();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            JdbcUtils.handleParams(ps, params);

            System.out.println(ps);

            rs = ps.executeQuery();

            return callBack.doExecute(conn, ps, rs);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBManager.close(ps, conn);
        }
    }

    /**
     * 执行一个 DML 语句
     *
     * @param sql    sql 语句
     * @param params 参数
     * @return 执行 sql 语句后影响的行数
     */
    int executeDml(String sql, Object[] params) {
        Connection conn = DBManager.getConn();
        int count = 0;

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);

            JdbcUtils.handleParams(ps, params);

            System.out.println(ps);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.close(ps, conn);
        }
        return count;
    }

    /**
     * 将一个对象存储到数据库中
     * 把对象中不为 null 的属性存储到数据库中，如果数字为 null 则放 0
     * 目前只能处理数据库维护主键自增的情况
     *
     * @param object 要储存的对象
     */
    void insert(Object object) {
        // insert into tname() valuse(?,?,?)

        Class<?> aClass = object.getClass();
        List<Object> params = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        String tname = tableInfo.getTname();

        StringBuilder sql = new StringBuilder("insert into ");
        // 不为空的属性值
        int countNotNullField = 0;
        sql.append(tname).append("(");

        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldName = declaredField.getName();
            Object fieldValue = ReflectUtils.invokeGetter(fieldName, object);
            if (fieldValue != null) {
                sql.append(fieldName).append(",");
                params.add(fieldValue);
                countNotNullField++;
            }
        }
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values(");
        for (int i = 0; i < countNotNullField; i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');

        executeDml(sql.toString(), params.toArray());
    }

    /**
     * 删除 clazz 表示类对应的表中的记录 （指定主键值id的记录）
     *
     * @param clazz 跟表对应的类的 Class 对象
     * @param id    主键的值
     */
    void delete(Class clazz, Object id) {
        // User.class,2 -> delete from user where id = 2

        // 通过 Class 对象找 TableInfo

        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        String sql = "delete from " + tableInfo.getTname() + " where " + onlyPriKey.getName() + "=? ";

        executeDml(sql, new Object[]{id});
    }

    /**
     * 删除对象在数据库中对应的记录 （对象所在的类对应到表，对象的主键值对应到记录）
     *
     * @param object
     */
    public void delete(Object object) {
        Class<?> aClass = object.getClass();

        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        Object o = ReflectUtils.invokeGetter(onlyPriKey.getName(), object);
        delete(aClass, o);

    }

    /**
     * 更新对象对应的记录，并且只更新指定的字段的值
     *
     * @param object     所更新的对象
     * @param fieldNames 更新的属性列表
     * @return 影响的行数
     */
    int update(Object object, String[] fieldNames) {
        // update tname set uname=?, ped=? where id=?
        Class<?> aClass = object.getClass();
        List<Object> params = new ArrayList<>();
        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        String tname = tableInfo.getTname();

        StringBuilder sql = new StringBuilder("update " + tname + " set ");

        for (String fieldName : fieldNames) {
            Object fValue = ReflectUtils.invokeGetter(fieldName, object);
            params.add(fValue);
            sql.append(fieldName).append("=?,");
        }
        sql.setCharAt(sql.length() - 1, ' ');
        sql.append("where ").append(onlyPriKey.getName()).append("=?");
        params.add(ReflectUtils.invokeGetter(onlyPriKey.getName(), object));

        return executeDml(sql.toString(), params.toArray());
    }

    /**
     * 查询返回多行记录，并且每行记录封装到clazz指定的对象中
     *
     * @param sql    sql 语句
     * @param clazz
     * @param params
     * @return
     */
    List queryRows(String sql, Class clazz, Object[] params) {

        return (List) executeQueryTemplate(sql, params, clazz, (conn, ps, rs) -> {

            List list = null;
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                // 处理多行
                while (rs.next()) {
                    if (list == null) {
                        list = new ArrayList();
                    }
                    Object rowObj = clazz.newInstance();
                    // 处理多列
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        String columnName = metaData.getColumnLabel(i + 1);
                        Object columnValue = rs.getObject(i + 1);

                        ReflectUtils.invokeSetter(rowObj, columnName, columnValue);
                    }
                    list.add(rowObj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        });
    }

    /**
     * 查询返回一行记录
     *
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);

        return (list == null && list.size() > 0) ? null : list.get(0);
    }

    /**
     * 查询返回一个值
     *
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Object queryValue(String sql, Class clazz, Object[] params) {

        return executeQueryTemplate(sql, params, null, (conn, ps, rs) -> {
            // 处理多行
            Object value = null;
            try {
                while (rs.next()) {
                    value = rs.getObject(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return value;
        });
    }

    /**
     * 查询返回一个数字
     *
     * @param sql
     * @param clazz
     * @param params
     * @return
     */
    Number queryNumber(String sql, Class clazz, Object[] params) {
        return (Number) queryValue(sql, clazz, params);
    }

    /**
     * 分页查询
     *
     * @param pageNum 第几页
     * @param size    每一页大小
     * @return
     */
    public abstract Object queryPagenate(int pageNum, int size);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
