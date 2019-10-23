package com.theo.sorm.utils;

import com.theo.sorm.bean.ColumnInfo;
import com.theo.sorm.bean.JavaFieldGetSet;
import com.theo.sorm.bean.TableInfo;
import com.theo.sorm.core.DBManager;
import com.theo.sorm.core.MysqlTypeConvertor;
import com.theo.sorm.core.TableContext;
import com.theo.sorm.core.TypeConvertor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 封装了生成 Java文件（源代码）的工具
 *
 * @author huangsuixin
 * @date 2019/10/21 22:04
 */
public class JavaFileUtils {

    /**
     * 根据字段信息生成Java属性信息，以及相应的set 和 get 方法源码
     *
     * @param column
     * @param typeConvertor
     * @return
     */
    public static JavaFieldGetSet createFieldGetSetSrc(ColumnInfo column, TypeConvertor typeConvertor) {

        JavaFieldGetSet javaFieldGetSet = new JavaFieldGetSet();

        String javaFieldType = typeConvertor.databaseType2JavaType(column.getDataType());

        javaFieldGetSet.setFieldInfo("\tprivate " + javaFieldType + " " + column.getName() + ";\n");

        StringBuilder getSrc = new StringBuilder();

        getSrc.append("\tpublic ")
                .append(javaFieldType)
                .append(" get")
                .append(StringUtils.firstCharUpperCase(column.getName()))
                .append("(){\n");
        getSrc.append("\t\treturn ").append(column.getName()).append(";\n").append("\t}");
        javaFieldGetSet.setGetInfo(getSrc.toString());

        StringBuilder setSrc = new StringBuilder();
        setSrc.append("\tpublic void set")
                .append(StringUtils.firstCharUpperCase(column.getName()))
                .append("(")
                .append(javaFieldType).append(" ").append(column.getName()).append("){\n");
        setSrc.append("\t\tthis.").append(column.getName()).append(" = ").append(column.getName()).append(";\n").append("\t}");
        javaFieldGetSet.setSetInfo(setSrc.toString());

        return javaFieldGetSet;
    }

    /**
     * 根据表信息生成 Java 类的源代码
     * @param tableInfo
     * @param typeConvertor
     * @return
     */
    public static String createJavaSrc(TableInfo tableInfo, TypeConvertor typeConvertor) {
        StringBuilder src = new StringBuilder();

        Map<String, ColumnInfo> columns = tableInfo.getColumns();

        List<JavaFieldGetSet> javaFieldGetSets = new ArrayList<>();

        columns.forEach((s, columnInfo) -> javaFieldGetSets.add(createFieldGetSetSrc(columnInfo, typeConvertor)));

        // package
        src.append("package ").append(DBManager.getConf().getPoPackage()).append(";\n\n");

        // import语句
        src.append("import java.sql.*;\n").append("import java.util.*;\n\n");

        // 类声明语句
        src.append("public class ").append(StringUtils.firstCharUpperCase(tableInfo.getTname())).append(" {\n\n");

        // 属性列表
        javaFieldGetSets.forEach(javaFieldGetSet -> src.append(javaFieldGetSet.getFieldInfo()));

        src.append("\n\n");

        // getter
        javaFieldGetSets.forEach(javaFieldGetSet -> src.append(javaFieldGetSet.getGetInfo()));

        // setter
        javaFieldGetSets.forEach(javaFieldGetSet -> src.append(javaFieldGetSet.getSetInfo()));

        src.append("}\n");

        return src.toString();

    }
    public static void createJavaPoFile(TableInfo tableInfo, TypeConvertor convertor) {
        String javaSrc = createJavaSrc(tableInfo, convertor);

        String srcPath = DBManager.getConf().getSrcPath() + "\\";
        String packagePath = DBManager.getConf().getPoPackage().replaceAll("\\.", "/");
        File file = new File(srcPath + packagePath);
        System.out.println(file.getAbsolutePath());

        if (!file.exists()) {
            file.mkdirs();
        }
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()+ "\\" + StringUtils.firstCharUpperCase(tableInfo.getTname()) + ".java"));
            writer.write(javaSrc);
            System.out.println("创建表 [" + tableInfo.getTname() + "] 对应的Java文件 - " + StringUtils.firstCharUpperCase(tableInfo.getTname()) + ".java");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {

                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        ColumnInfo ci = new ColumnInfo().setName("username").setDataType("varchar").setKeyType(0);
//        JavaFieldGetSet fieldGetSetSrc = createFieldGetSetSrc(ci, new MysqlTypeConvertor());
//        System.out.println(fieldGetSetSrc);

        Map<String, TableInfo> tables = TableContext.tables;
        TableInfo user = tables.get("user");
        createJavaPoFile(user, new MysqlTypeConvertor());

    }
}
