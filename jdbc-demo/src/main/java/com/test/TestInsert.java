package com.test;

import com.util.DBUtil;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestInsert {

    @Test
    public void insert() {
        String sql = "insert into user(name,age,sex) values(?,?,?)";
        PreparedStatement ps = DBUtil.executePreparedStatement(sql);
        try {
            ps.setString(1, "张XX");
            ps.setInt(2, 20);
            ps.setString(3, "男");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                DBUtil.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
