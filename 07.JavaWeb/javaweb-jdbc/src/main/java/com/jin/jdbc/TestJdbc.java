package com.jin.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //配置信息
        //useUnicode=true&characterEncoding=utf8 解决中文乱码
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=UTF-8&useSSL=True";
        String username = "root";
        String password = "Zjh#5137";

        //1. 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2. 连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);
        //3. 向数据库发送SQL的对象statement: CRUD
        Statement statement = connection.createStatement();
        //4. 编写SQL
        String sql = "select * from users";
        //5. 执行查询SQL，返回一个结果集
        //增删改用statement.executeUpdate
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println("id=" + rs.getObject("id"));
            System.out.println("name=" + rs.getObject("name"));
            System.out.println("password=" + rs.getObject("password"));
            System.out.println("email=" + rs.getObject("email"));
            System.out.println("birthday=" + rs.getObject("birthday"));
        }
        //6. 关闭连接，释放资源（一定要做），先开后关
        rs.close();
        statement.close();
        connection.close();
    }
}
