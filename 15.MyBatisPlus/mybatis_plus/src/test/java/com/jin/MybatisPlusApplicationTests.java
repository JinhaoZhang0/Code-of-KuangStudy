package com.jin;

import com.jin.mapper.UserMapper;
import com.jin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    //继承了BaseMapper，所有的方法都来自父类，
    //我们也可以编写自己的扩展方法！
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数是一个Wrapper，条件构造器，这里我们先不用，null
        //查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    //测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("狂神说Java");
        user.setAge(3);
        user.setEmail("24736743@qq.com");

        int result = userMapper.insert(user); //帮我们自动生成id
        System.out.println(result); //受影响的行数
        System.out.println(user); //发现，id会自动回填
    }
}
