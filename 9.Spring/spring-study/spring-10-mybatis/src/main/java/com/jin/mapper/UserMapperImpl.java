package com.jin.mapper;

import com.jin.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class UserMapperImpl implements UserMapper{
    //原来我们的所有操作，都使用sqlSession来执行，现在都使用sqlSessionTemplate
    private SqlSessionTemplate sqlSession;

    //使spring能够接管
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
        return sqlSession.getMapper(UserMapper.class).selectUser();
    }
}
