package com.lesson03;

import com.lesson02.utils.JdbcUtils;

import java.util.Date;
import java.sql.*;

public class TestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils.getConnection();

            // 区别
            // 使用 ? 占位符代替参数
            String sql = "insert into users(id,`NAME`,`PASSWORD`,`email`,`birthday`) values(?,?,?,?,?)";
            st = conn.prepareStatement(sql); //预编译SQL，先写SQL，然后不执行

            // 手动给参数赋值
            st.setInt(1, 4);
            st.setString(2, "qinjiang");
            st.setString(3, "1232112");
            st.setString(4, "24734673@qq.com");
            // 注意点： sql.Date   数据库        java.sql.Date()
            //        util.Date  Java         new Date().getTime() 获得时间戳
            st.setDate(5, new java.sql.Date(new java.util.Date().getTime()));

            // 执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("插入成功！");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(conn,st,null);
        }
    }
}
