package com.jin.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jin.pojo.User;
import com.jin.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @RequestMapping("/j1")
    public String json1() throws JsonProcessingException {
        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("关羽",3,"男");

        String str = mapper.writeValueAsString(user);
        return str;
    }

    @RequestMapping("/j2")
    public String json2() throws JsonProcessingException {
        List<User> userList = new ArrayList<>();
        //创建一个对象
        User user1 = new User("关羽",1,"男");
        User user2 = new User("张飞",2,"男");
        User user3 = new User("赵云",3,"男");
        User user4 = new User("马超",4,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JsonUtils.getJson(userList);
    }

    @RequestMapping("/j3")
    public String json3() throws JsonProcessingException {
        Date date = new Date();

        return JsonUtils.getJson(date);
    }

    @RequestMapping("/j4")
    public String json4() throws JsonProcessingException {
        List<User> userList = new ArrayList<>();
        User user1 = new User("关羽",1,"男");
        User user2 = new User("张飞",2,"男");
        User user3 = new User("赵云",3,"男");
        User user4 = new User("马超",4,"男");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        return JSON.toJSONString(userList);
    }
}
