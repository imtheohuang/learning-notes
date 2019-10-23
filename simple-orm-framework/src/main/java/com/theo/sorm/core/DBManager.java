package com.theo.sorm.core;

import com.theo.sorm.bean.Configuration;
import com.theo.sorm.pool.DBConnPool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 *
 * @author huangsuixin
 * @date 2019/10/21 22:02
 */
public class DBManager {

    private static Configuration conf;

    private static DBConnPool pool;

    static {
        Properties properties = new Properties();

        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        conf = new Configuration();
        conf.setDriver(properties.getProperty("driver"));
        conf.setPoPackage(properties.getProperty("poPackage"));
        conf.setUrl(properties.getProperty("url"));
        conf.setUser(properties.getProperty("user"));
        conf.setPwd(properties.getProperty("pwd"));
        conf.setUsingDB(properties.getProperty("usingDB"));
        conf.setSrcPath(properties.getProperty("srcPath"));
        conf.setQueryClass(properties.getProperty("queryClass"));
        conf.setPoolMaxSize(Integer.valueOf(properties.getProperty("poolMaxSize")));
        conf.setPoolMinSize(Integer.valueOf(properties.getProperty("poolMinSize")));

    }

    /**
     * 获取连接对象
     *
     * @return Connection
     */
    public static Connection getConn() {
        if (pool == null) {
            pool = new DBConnPool();
        }
        return pool.getConnection();
    }


    /**
     * 创建一个新的连接
     *
     * @return {@link Connection}
     */
    public static Connection createConn() {
        try {
            Class.forName(conf.getDriver());
            // 直接建立连接，后期使用连接池
            return DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Configuration getConf() {
        return conf;
    }

    /**
     * 关闭对象
     *
     * @param closeable closeable
     */
    public static void close(AutoCloseable... closeable) {
        for (AutoCloseable closeable1 : closeable) {
            if (null != closeable1) {
                if (closeable1 instanceof Connection) {
                    pool.close((Connection) closeable1);
                } else {
                    try {
                        closeable1.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
