package com.jin.dao;

import com.jin.pojo.User;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserByIdTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUserById(1);

                System.out.println(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
