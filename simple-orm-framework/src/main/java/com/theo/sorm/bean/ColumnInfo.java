package com.theo.sorm.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 封装了表中字段信息
 * @author huangsuixin
 * @date 2019/10/21 22:05
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ColumnInfo {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 字段的数据类型
     */
    private String dataType;

    /**
     * 字段键类型 0普通键； 1主键；2外键
     */
    private int keyType;
}
