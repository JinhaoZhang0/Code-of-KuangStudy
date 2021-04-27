package com.jin.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int tid;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                '}';
    }
}
