package com.example.javaweblab3.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DruidUtil {
    private static DruidDataSource ds;

    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
            prop.setProperty("url", "jdbc:mysql://localhost:3306/javaweb_lab3?characterEncoding=UTF8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
            prop.setProperty("username", "root");
            prop.setProperty("password", "123456");
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn,Statement stmt) {
        close(conn, stmt, null);
    }
}
