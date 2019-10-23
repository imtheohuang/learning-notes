package com.theo.sorm.core;

import com.theo.sorm.bean.TableInfo;

/**
 * MySQL 数据类型  <==> java 类型
 *
 * @author huangsuixin
 * @date 2019/10/22 13:53
 */
public class MysqlTypeConvertor implements TypeConvertor {
    @Override
    public String databaseType2JavaType(String columnType) {

        if ("varchar".equalsIgnoreCase(columnType) || "char".equalsIgnoreCase(columnType)) {
            return "String";
        }
        if ("int".equalsIgnoreCase(columnType)
                || "tinyint".equalsIgnoreCase(columnType)
                || "smallint".equalsIgnoreCase(columnType)
                || "integer".equalsIgnoreCase(columnType)) {
            return "Integer";
        }

        if ("bigint".equalsIgnoreCase(columnType)) {
            return "Long";
        }
        if ("double".equalsIgnoreCase(columnType)) {
            return "Double";
        }

        if ("clob".equalsIgnoreCase(columnType)) {
            return "java.sql.CLob";
        }

        if ("blob".equalsIgnoreCase(columnType)) {
            return "java.sql.BLob";
        }

        if ("date".equalsIgnoreCase(columnType)) {
            return "java.sql.Date";
        }

        if ("time".equalsIgnoreCase(columnType)) {
            return "java.sql.Time";
        }
        if ("timestamp".equalsIgnoreCase(columnType)) {
            return "java.sql.Timestamp";
        }
        return null;
    }



    @Override
    public String javaType2DatabaseType(String javaDataType) {
        return null;
    }
}
