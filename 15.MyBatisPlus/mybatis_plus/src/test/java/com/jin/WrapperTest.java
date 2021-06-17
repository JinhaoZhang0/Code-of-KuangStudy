package com.jin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jin.mapper.UserMapper;
import com.jin.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询name不为空，并且邮箱不为空，年龄大于等于12的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 12);
        userMapper.selectList(wrapper).forEach(System.out::println); //和我们刚才学习的map对比一下
    }

    @Test
    void test2() {
        //查询名字
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "狂神说");
        User user = userMapper.selectOne(wrapper); //查询一个数据，出现多个结果使用List或者Map
        System.out.println(user);
    }

    @Test
    void test3() {
        //查询年龄在20～30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30); //区间
        Integer count = userMapper.selectCount(wrapper);//查询结果数
        System.out.println(count);
    }

    // 模糊查询
    @Test
    void test4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //左 %e 和右 e%
        wrapper
                .notLike("name", "e")
                .likeRight("email", "t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    // 嵌套查询
    @Test
    void test5() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id在子查询中查出来
        wrapper.inSql("id", "select id from user where id<3");
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    //排序
    @Test
    void test6() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id降序排序
        wrapper.orderByDesc("id");
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}


