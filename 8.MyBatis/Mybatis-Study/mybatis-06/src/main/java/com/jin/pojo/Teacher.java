package com.jin.pojo;

import lombok.Data;

@Data
public class Teacher {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
