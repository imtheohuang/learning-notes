package com.theo.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author huangsuixin
 * @date 2019/10/23 12:10
 */
public interface CallBack {
    Object doExecute(Connection conn, PreparedStatement ps, ResultSet rs);
}
