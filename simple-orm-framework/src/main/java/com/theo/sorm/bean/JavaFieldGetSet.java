package com.theo.sorm.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 封装了Java属性和setter getter
 *
 * @author huangsuixin
 * @date 2019/10/22 14:01
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class JavaFieldGetSet {
    /**
     * 属性源码信息 eg: private String id;
     */
    private String fieldInfo;

    /**
     * getter 方法信息 eg: public Integer getId();
     */
    private String getInfo;

    /**
     * setter 方法信息 eg: public void setId();
     */

    private String setInfo;
}
