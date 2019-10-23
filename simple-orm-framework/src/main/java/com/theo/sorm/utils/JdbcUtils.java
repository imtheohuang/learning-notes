package com.theo.sorm.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装常用的 JDBC 操作
 * @author huangsuixin
 * @date 2019/10/21 22:03
 */
public class JdbcUtils {

    public static void handleParams(PreparedStatement ps, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject( 1 + i, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
