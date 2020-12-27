package com.util;

import java.sql.*;

@SuppressWarnings("unused")

public class DBUtil {

    public static final String URL = "jdbc:mysql://localhost:3306/jdbc?useSSL=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "mysql";

    protected static Statement s = null;
    protected static ResultSet rs = null;
    protected static Connection conn = null;

    /**
     * 创建数据库链接
     *
     * @return Connection
     */
    public static synchronized Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行insert update delete语句
     *
     * @param sql sql
     * @return 执行结果 int
     */
    public static int executeUpdate(String sql) {
        int result = 0;
        try {
            s = getConnection().createStatement();
            result = s.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行select语句
     *
     * @param sql sql
     * @return result结果集
     */
    public static ResultSet executeQuery(String sql) {
        try {
            s = getConnection().createStatement();
            rs = s.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }


    /**
     * 执行动态sql语句
     *
     * @param sql sql
     * @return PreparedStatement
     */
    public static PreparedStatement executePreparedStatement(String sql) {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }


    /**
     * *事务回滚
     */
    public static void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * *关闭连接对象
     */
    public static void close() {
        try {
            if (rs != null)
                rs.close();
            if (s != null)
                s.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}