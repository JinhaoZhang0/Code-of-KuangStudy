package com.tvolatile;

import java.util.concurrent.atomic.AtomicInteger;

// 不保证原子性
public class Vdemo02 {
    // volatile 不保证原子性
    // 原子类的integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add(){
//        num++; //不是原子型操作
        num.getAndIncrement();  // AtomicInteger + 1 方法， CAS
    }

    // 理论上结果应该是2万
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+" "+ num);
    }
}
