package com.test;

import com.util.DBUtil;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDelete {

    @Test
    public void delete() {
        String sql = "delete from user where id=?";
        PreparedStatement ps = DBUtil.executePreparedStatement(sql);
        try {
            ps.setInt(1, 4);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
