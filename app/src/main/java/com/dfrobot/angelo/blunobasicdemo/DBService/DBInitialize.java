package com.dfrobot.angelo.blunobasicdemo.DBService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBInitialize {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://39.106.80.63:3306/vehicle_database?characterEncoding=utf8";//MYSQL数据库连接Url
    private static String user = "";//用户名
    private static String password = "";//密码
/*
    db.driverClassName=com.mysql.jdbc.Driver
    db.url=
    db.username=
    db.password=mysql*/


    /**
     * 连接数据库
     * @return
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn =  DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭数据库
     * @param conn
     * @param ps
     */
    public static void closeAll(Connection conn, PreparedStatement ps) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 关闭数据库
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
 
