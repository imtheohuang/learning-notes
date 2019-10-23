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
public class MysqlQuery extends Query {

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

    @Override
    public Object queryPagenate(int pageNum, int size) {
        return null;
    }
}
