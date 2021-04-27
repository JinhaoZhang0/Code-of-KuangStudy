package com.jin.dao;

import com.jin.pojo.User;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class UserMapperTest {

    static Logger logger = Logger.getLogger(UserMapperTest.class);
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

    @Test
    public void getUserByLimit() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            HashMap<String,Integer> map = new HashMap<>();
            map.put("startIndex",0);
            map.put("pageSize",2);
            List<User> userList = userMapper.getUserByLimit(map);
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByRowBounds() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //RowBounds实现
            RowBounds rowBounds = new RowBounds(1,2);

            //通过Java代码层面而实现分页
            List<User> userList = sqlSession.selectList("com.jin.dao.UserMapper.getUserByRowBounds",null,rowBounds);

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLog4j(){
        logger.info("info:进入了testLog4j");
        logger.debug("debug:进入了testLog4j");
        logger.error("error:进入了testLog4j");
    }

}
