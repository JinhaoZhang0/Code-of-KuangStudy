package com.jin.dao;

import com.jin.pojo.User;
import com.jin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMapperTest {
//    @Test
//    public void getUserLikeTest() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
//            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//            List<User> userList = mapper.getUserLike("李");
//            for (User user : userList) {
//                System.out.println(user);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void getUserListTest() {
        //第一步：获得sqlSession对象
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) { //try with resources
            //方式一：getMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.getUserList();

            //方式二：不推荐使用
//        List<User> userList = sqlSession.selectList("com.jin.dao.UserDao.getUserList");

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByIdTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUserTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int rows = mapper.addUser(new User(4, "哈哈", "123333"));
            if (rows > 0) {
                System.out.println("插入成功！");
            }
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void addUser2Test() {
//        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
//            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//            Map<String, Object> map = new HashMap<>();
//            map.put("userid", 5);
//            map.put("username", "Hello");
//            map.put("password", "223333");
//            int rows = mapper.addUser2(map);
//            if (rows > 0) {
//                System.out.println("插入成功！");
//            }
//            //提交事务
//            sqlSession.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void updateUserTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.updateUser(new User(4, "呵呵", "123123"));
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteUserTest() {
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            mapper.deleteUser(4);
            //提交事务
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
