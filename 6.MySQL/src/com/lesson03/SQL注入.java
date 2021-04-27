package com.lesson03;

import com.lesson02.utils.JdbcUtils;

import java.sql.*;

public class SQL注入 {
    public static void main(String[] args) {
//        login("lisi","123456");
        login(" ''or 1=1", "''or 1=1"); //技巧
    }
    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection(); //获得数据库连接
            // PreparedStatement 防止SQL注入的本质，把传递进来的参数当作字符
            // 假设其中存在转义字符，比如说 ' 会被直接转义
            String sql = "select * from users where `NAME`= ? and `PASSWORD` = ?";  //Mybatis

            st = conn.prepareStatement(sql); //预编译
            st.setString(1, username);
            st.setString(2, password);

            rs = st.executeQuery(); //查询完毕会返回一个结果集
            while (rs.next()){
                System.out.println(rs.getString("NAME"));
                System.out.println(rs.getString("password"));
                System.out.println("=========================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

}
