package com.lesson03;

import com.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 使用 ? 占位符代替参数
            String sql = "update users set `NAME` = ? where id=?";
            st = conn.prepareStatement(sql); //预编译SQL，先写SQL，然后不执行

            // 手动给参数赋值
            st.setString(1, "狂神");
            st.setInt(2, 1);

            // 执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("更新成功！");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, null);
        }
    }
}
