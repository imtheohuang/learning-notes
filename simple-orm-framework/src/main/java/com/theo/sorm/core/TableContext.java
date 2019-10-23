package com.theo.sorm.core;

import com.mysql.cj.api.mysqla.result.Resultset;
import com.theo.sorm.bean.ColumnInfo;
import com.theo.sorm.bean.TableInfo;
import com.theo.sorm.utils.JavaFileUtils;
import com.theo.sorm.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 负责获取管理数据库所有表结构和类结构的关系，并可以根据表结构生成类结构。
 *
 * @author huangsuixin
 * @date 2019/10/21 22:01
 */
public class TableContext {
    /**
     * 表名为 key，表信息对象为 value
     */
    public static Map<String, TableInfo> tables = new HashMap<>();

    /**
     * 将 po 的 class 对象和表信息关联起来，便于重用
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<>();

    static {
        try {
            Connection conn = DBManager.getConn();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tableSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});

            while (tableSet.next()) {
                String tableName = (String) tableSet.getObject("TABLE_NAME");
                TableInfo ti = new TableInfo()
                        .setTname(tableName)
                        .setPrikeys(new ArrayList<>())
                        .setColumns(new HashMap<>());
                tables.put(tableName, ti);

                // 查询表中所有字段
                ResultSet columns = metaData.getColumns(null, "%", tableName, "%");
                while (columns.next()) {
                    ColumnInfo columnInfo = new ColumnInfo()
                            .setName(columns.getString("COLUMN_NAME"))
                            .setDataType(columns.getString("TYPE_NAME"))
                            .setKeyType(0);
                    ti.getColumns().put(columns.getString("COLUMN_NAME"), columnInfo);
                }

                // 查询表中的主键
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, "%", tableName);
                while (primaryKeys.next()) {

                    ColumnInfo primaryKey = ti.getColumns().get(primaryKeys.getObject("COLUMN_NAME"));
                    primaryKey.setKeyType(1);
                    ti.getPrikeys().add(primaryKey);
                }

                // 取唯一主键，方便使用，如果是联合主键，则为空
                if (ti.getPrikeys().size() > 0) {
                    ti.setOnlyPriKey(ti.getPrikeys().get(0));
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 更新类结构
        updateJavaPoFile();

        loadPoTables();
    }

    /**
     * 根据表结构，更新配置的表的映射Java文件
     */
    public static void updateJavaPoFile() {
        Map<String, TableInfo> tables = TableContext.tables;
        tables.forEach((s, tableInfo) -> {
            JavaFileUtils.createJavaPoFile(tableInfo, new MysqlTypeConvertor());
        });
    }

    /**
     * 加载 po包下的类
     */
    public static void loadPoTables() {

        tables.forEach((s, tableInfo) -> {
            try {

                Class c = Class.forName(DBManager.getConf().getPoPackage() + "." + StringUtils.firstCharUpperCase(tableInfo.getTname()));
                poClassTableMap.put(c, tableInfo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        Map<String, TableInfo> tables = TableContext.tables;
        System.out.println(tables);
    }
}
