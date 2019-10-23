package com.theo.sorm.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 表结构信息
 * @author huangsuixin
 * @date 2019/10/21 22:08
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TableInfo {
    /**
     * 表名
     */
    private String tname;

    /**
     * 所有字段信息
     */
    private Map<String, ColumnInfo> columns;

    /**
     * 唯一主键（目前只能处理只有一个主键的情况）
     */
    private ColumnInfo onlyPriKey;

    /**
     * 联合主键
     */
    private List<ColumnInfo> prikeys;

}
