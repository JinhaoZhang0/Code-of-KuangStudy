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

    //测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        //通过条件自动拼接动态sql
        user.setId(7L);
        user.setName("狂神说");
        user.setAge(20);

        //注意；userMapper 参数是一个对象！
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试乐观锁成功！
    @Test
    public void testOptimisticLocker() {
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息
        user.setName("Kuangshen");
        user.setEmail("23736743@qq.com");
        //3.执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁失败！多线程下
    @Test
    public void testOptimisticLocker2() {
        //线程1
        User user = userMapper.selectById(1L);
        user.setName("Kuangshen111");
        user.setEmail("23736743@qq.com");

        //模拟另外一个线程执行插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("Kuangshen222");
        user2.setEmail("23736743@qq.com");
        userMapper.updateById(user2);

        //可以使用自旋锁多次尝试提交
        userMapper.updateById(user); //如果没有乐观锁就会覆盖插队线程的值
    }
}
