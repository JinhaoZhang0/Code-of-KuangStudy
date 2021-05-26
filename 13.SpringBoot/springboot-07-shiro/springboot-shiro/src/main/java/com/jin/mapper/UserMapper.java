package com.jin.mapper;

import com.jin.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    User queryUserByName(String name);
}
