package com.lesson01;

import java.sql.*;

// 我的第一个jdbc程序
public class jdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. 加载驱动    mysql8.0以上
        Class.forName("com.mysql.cj.jdbc.Driver"); // 固定写法，加载驱动 5.0没有cj

        //2. 用户信息和url
        // useUnicode=true&characterEncoding=UTF-8&useSSL=true
        String url = "jdbc:mysql://localhost:3306/jdbcStudy?useUnicode=true&characterEncoding=UTF-8&useSSL=True";
        String username = "root";
        String password = "Zjh#5137";

        //3. 连接成功，数据库对象    Connection 代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        //4. 执行SQL的对象
        Statement statement = connection.createStatement();

        //5. 执行SQL的对象 去执行SQL，可能存在结果，查看返回结果
        String sql = "SELECT * FROM users";
        ResultSet resultSet = statement.executeQuery(sql); // 返回结果集，结果集中封装了我们全部的查询出来的结果

        while (resultSet.next()) {
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birth=" + resultSet.getObject("birthday"));
            System.out.println("=================================");
        }

        //6. 释放连接
        resultSet.close();
        statement.close();
        connection.close();

//        statement.executeQuery();   //查询操作返回 ResultSet
//        statement.execute();        //执行任何SQL
//        statement.executeUpdate();  //更新，插入，删除。都是用这个，返回一个受影响的行数
//        resultSet.getObject();  // 在不知道列类型的时候使用
//        resultSet.getString();
//        resultSet.getInt();
//        resultSet.getFloat();
//        resultSet.getDouble();
//        resultSet.beforeFirst();    //移动到最前面
//        resultSet.afterLast();      //移动到最后面
//        resultSet.next();           //移动到下一个数据
//        resultSet.previous();       //移动到前一行
//        resultSet.absolute(row);    //移动到指定行

    }
}
