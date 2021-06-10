package com.jin.springcloud.service;

import com.jin.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();

    @PostMapping("/dept/add")
    boolean addDept(Dept dept);
}
