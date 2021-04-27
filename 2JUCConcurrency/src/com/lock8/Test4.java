package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 7. 1个静态同步方法，1个普通同步方法，1个对象，先打印 发短信还是打电话？  打电话！
 * 8. 1个静态同步方法，1个普通同步方法，2个对象，先打印 发短信还是打电话？  打电话！
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象 类模板只有一个
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        // 锁的存在
        new Thread(()->{
            phone1.sendSms();
        },"A").start();

        // 捕获
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}

// Phone3 唯一的一个class对象
class Phone4{

    // sychoronized 锁的对象是方法的调用者！
    // static 静态方法
    // 类一加载就有！ 锁的是Class
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    // 普通同步方法
    public synchronized void call(){
        System.out.println("打电话");
    }

}