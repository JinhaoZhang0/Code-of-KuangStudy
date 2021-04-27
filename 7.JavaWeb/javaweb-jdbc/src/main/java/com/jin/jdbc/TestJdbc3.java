package com.jin.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc3 {

    @Test
    public void test(){
        //配置信息
        //useUnicode=true&characterEncoding=utf8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=UTF-8&useSSL=True";
        String username = "root";
        String password = "Zjh#5137";

        Connection connection = null;
        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 连接数据库,代表数据库
            connection = DriverManager.getConnection(url, username, password);
            //3. 通知数据库开启事务 false是开启
            connection.setAutoCommit(false);
            String sql = "update account set money = money-100 where name = 'A'";
            connection.prepareStatement(sql).executeUpdate();
            //制造错误
            int i =1/0;
            String sql2 = "update account set money = money+100 where name = 'B'";
            connection.prepareStatement(sql2).executeUpdate();
            connection.commit(); //以上两条SQL都执行成功了，就提交事务！
            System.out.println("提交成功");
        } catch (Exception e) {
            try {
                //如果出现异常，就通知数据库回滚事务
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
