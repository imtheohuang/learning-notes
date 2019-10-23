package com.theo.sorm.core;

/**
 * Java 数据类型 <==> 数据库类型
 * @author huangsuixin
 * @date 2019/10/21 21:59
 */
public interface TypeConvertor {
    /**
     * Java 数据类型 <== 数据库类型
     * @param columnType
     * @return
     */
    String databaseType2JavaType(String columnType);

    /**
     * Java 数据类型 ==> 数据库类型
     * @param javaDataType
     * @return
     */
    String javaType2DatabaseType(String javaDataType);
}
