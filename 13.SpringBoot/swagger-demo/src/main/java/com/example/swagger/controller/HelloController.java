package com.example.swagger.controller;

import com.example.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello, swagger";
    }

    @PostMapping("/getUser")
    public User getUser(){
        return new User();
    }

    @ApiOperation("hello的接口")
    @GetMapping(value = "/hello2")
    public String hello2(@ApiParam("用户名")String username){
        return "hello" + username;
    }
}
