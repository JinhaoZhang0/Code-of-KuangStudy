package com.jin.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true) //链式写法
public class Dept implements Serializable { //Dept 实体类 orm 类表关系映射
    private Long deptno; //主键
    private String dname;
    //这个数据是存在那个数据库的字段～ 微服务，一个服务对应一个数据库，同一个信息可能存在不同的数据库
    private String db_source;

    public Dept() {
    }

    public Dept(String dname) {
        this.dname = dname;
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDb_source() {
        return db_source;
    }

    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }

    /*
        链式写法：
        Dept dept = new Dept();

        dept.setDeptNo(11).setDname('ss').setDb_source('001')
     */
}