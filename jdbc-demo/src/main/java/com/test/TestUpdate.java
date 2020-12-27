package com.test;

import com.util.DBUtil;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {

    @Test
    public void update() {
        String sql = "update user set age=age+1 where id=?;";
        PreparedStatement ps = DBUtil.executePreparedStatement(sql);
        try {
            ps.setInt(1, 1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtil.close();
        }
    }
}
