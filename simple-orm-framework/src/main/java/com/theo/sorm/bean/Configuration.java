package com.theo.sorm.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 管理配置信息
 * @author huangsuixin
 * @date 2019/10/21 22:07
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Configuration {

    /**
     * 驱动类
     */
    private String driver;
    /**
     * jdbc 的 url
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String user;
    /**
     * 数据库密码
     */
    private String pwd;
    /**
     * 正在使用那个数据库
     */
    private String usingDB;
    /**
     * 项目的源码路径
     */
    private String srcPath;
    /**
     * 扫描生成 Java类的包 持久化对象
     */
    private String poPackage;

    /**
     * 项目使用的查询是哪一个类
     * <p>? extent {@link com.theo.sorm.core.Query}</p>
     */
    private String queryClass;

    /**
     * 连接池中最小的连接数
     */
    private int poolMinSize;

    /**
     * 连接池中最大的连接数
     */
    private int poolMaxSize;
}
