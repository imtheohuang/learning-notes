package com.theo.sorm.pool;

import com.theo.sorm.core.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接池的类
 *
 * @author huangsuixin
 * @date 2019/10/23 14:02
 */
public class DBConnPool {

    /**
     * 连接池对象
     */
    private List<Connection> pool;

    /**
     * 最大连接数
     */
    private static final int POOL_MAX_SIZE = DBManager.getConf().getPoolMaxSize();

    /**
     * 最小连接数
     */
    private static final int POOL_MIN_SIZE = DBManager.getConf().getPoolMinSize();

    /**
     * 初始化连接池
     */
    private void initPool() {
        if (pool == null) {
            pool = new ArrayList<>();
        }

        while (pool.size() < POOL_MIN_SIZE) {
            pool.add(DBManager.createConn());
            System.out.println("初始化连接池，池中连接数：" + pool.size());
        }
    }

    public DBConnPool() {
        initPool();
    }

    /**
     * 从连接池中取出一个连接
     * @return {@link Connection}
     */
    public synchronized Connection getConnection() {
        int lastIndex = pool.size();
        Connection connection = pool.get(lastIndex);
        pool.remove(lastIndex);
        return connection;
    }

    /**
     * 并不是真正的关闭， 将连接放回到连接池中
     * @param conn {@link Connection}
     */
    public synchronized void close(Connection conn) {
        if (pool.size() >= POOL_MAX_SIZE) {
            try {
                if (conn != null) {
                    conn.close();
                }
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            pool.add(conn);
        }
    }
}
