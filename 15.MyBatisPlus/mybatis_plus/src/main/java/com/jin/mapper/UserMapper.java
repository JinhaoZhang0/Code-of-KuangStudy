package com.jin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jin.pojo.User;
import org.springframework.stereotype.Repository;

// 在对应的Mapper上面继承基本的接口BaseMapper
@Repository //代表持久层
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD操作都已经编写完成了
    //你不需要像以前配置一大堆文件了
}
