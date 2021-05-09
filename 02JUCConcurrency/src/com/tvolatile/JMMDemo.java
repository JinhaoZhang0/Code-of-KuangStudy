package com.tvolatile;

import java.util.concurrent.TimeUnit;

public class JMMDemo {

    // add volatile 不加就会死循环， 保证可见性
    private volatile static int num = 0;
    public static void main(String[] args) { //main

        new Thread(()->{ //线程1 对主内存变化不知道
            while(num==0){

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;

        System.out.println(num);
    }
}
