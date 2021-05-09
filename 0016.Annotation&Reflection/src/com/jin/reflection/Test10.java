package com.jin.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//分析性能问题
public class Test10 {
    //普通方法调用
    public static void test01() {
        User user = new User();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方法执行10亿次："+(endTime-startTime)+"ms");
    }

    //反射方法调用
    public static void test02() throws Exception {
        User user = new User();
        Class c1 = user.getClass();
        long startTime = System.currentTimeMillis();

        Method getName = c1.getDeclaredMethod("getName",null);

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方法执行10亿次："+(endTime-startTime)+"ms");
    }

    //反射方法调用 关闭检测
    public static void test03() throws Exception {
        User user = new User();
        Class c1 = user.getClass();
        long startTime = System.currentTimeMillis();

        Method getName = c1.getDeclaredMethod("getName",null);
        getName.setAccessible(true);

        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射方法（关闭检测）执行10亿次："+(endTime-startTime)+"ms");
    }

    public static void main(String[] args) throws Exception {
        test01();
        test02();
        test03();
    }
}
