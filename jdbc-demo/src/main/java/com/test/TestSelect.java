package com.test;

import com.com.pojo.User;
import com.util.DBUtil;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestSelect {

    @Test
    // 简单查询一个结果
    public void simpleSelectOne() {
        String sql = "select * from user where name = '%s'";
        sql = String.format(sql, "张一");
        // 执行查询
        ResultSet rs = DBUtil.executeQuery(sql);
        User user = new User();
        try {
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
            } else {
                System.out.println("没有此用户");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close();
        }
        System.out.println(user);
    }


    @Test
    // 简单查询多个
    public void simpleSelectAll() {
        String sql = "select * from user";
        List<User> users = new ArrayList<User>();
        ResultSet rs = DBUtil.executeQuery(sql);
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close();
        }
        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void select() {
        String sql = "select * from user where name=?";
        PreparedStatement ps = DBUtil.executePreparedStatement(sql);
        ResultSet rs = null;
        User user = new User();
        try {
            ps.setString(1, "张二");
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
            } else {
                System.out.println("没有用户");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close();
        }
        System.out.println(user);
    }

}
