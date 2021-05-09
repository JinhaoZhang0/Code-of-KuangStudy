package com.lesson02;

import com.lesson02.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL注入 {
    public static void main(String[] args) {
//        login("kuangshen","123456");
        login(" 'or '1=1", "'or '1=1"); //技巧
    }
    //登录业务
    public static void login(String username, String password) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection(); //获得数据库连接
            st = conn.createStatement(); //获得SQL的执行对象

            //SELECT * FROM users WHERE `Name` = 'kuangshen' AND `password` = '123456';
            //SELECT * FROM users WHERE `Name` = ''or '1=1' AND `password` = ''or '1=1';
            String sql = "select * from users where `NAME`= '" + username + "' AND `password` = '" + password + "'";

            rs = st.executeQuery(sql); //查询完毕会返回一个结果集

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
