package com.theo.sorm.core;

import com.theo.po.User;
import com.theo.sorm.bean.ColumnInfo;
import com.theo.sorm.bean.TableInfo;
import com.theo.sorm.utils.JdbcUtils;
import com.theo.sorm.utils.ReflectUtils;
import com.theo.sorm.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Mysql 数据库的查询
 *
 * @author huangsuixin
 * @date 2019/10/23 10:10
 */
public class MysqlQuery implements Query {
    @Override
    public int executeDml(String sql, Object[] params) {
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

    @Override
    public void insert(Object object) {
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

    @Override
    public void delete(Class clazz, Object id) {
        // User.class,2 -> delete from user where id = 2

        // 通过 Class 对象找 TableInfo

        TableInfo tableInfo = TableContext.poClassTableMap.get(clazz);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();

        String sql = "delete from " + tableInfo.getTname() + " where " + onlyPriKey.getName() + "=? ";

        executeDml(sql, new Object[]{id});
    }

    @Override
    public void delete(Object object) {
        Class<?> aClass = object.getClass();

        TableInfo tableInfo = TableContext.poClassTableMap.get(aClass);
        ColumnInfo onlyPriKey = tableInfo.getOnlyPriKey();
        Object o = ReflectUtils.invokeGetter(onlyPriKey.getName(), object);
        delete(aClass, o);

    }

    @Override
    public int update(Object object, String[] fieldNames) {
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

    @Override
    public List queryRows(String sql, Class clazz, Object[] params) {
        Connection conn = DBManager.getConn();
        List list = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            JdbcUtils.handleParams(ps, params);

            System.out.println(ps);

            rs = ps.executeQuery();
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
        } finally {
            DBManager.close(ps, conn);
        }
        return list;
    }

    @Override
    public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
        List list = queryRows(sql, clazz, params);

        return (list == null && list.size() > 0) ? null : list.get(0);
    }

    @Override
    public Object queryValue(String sql, Class clazz, Object[] params) {
        Connection conn = DBManager.getConn();
        Object value = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            JdbcUtils.handleParams(ps, params);

            System.out.println(ps);

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();

            // 处理多行
            while (rs.next()) {
                value = rs.getObject(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBManager.close(ps, conn);
        }
        return value;
    }

    @Override
    public Number queryNumber(String sql, Class clazz, Object[] params) {
        return (Number) queryValue(sql, clazz, params);
    }

    public static void main(String[] args) {
        List list = new MysqlQuery().queryRows("select * from user", User.class, null);
        list.forEach(o -> System.out.println(((User) o).getName()));
    }

    public static void testDml() {
        User u = new User();
        u.setId(4);
        u.setName("小小世界1");

        new MysqlQuery().update(u, new String[]{"name"});
    }
}
