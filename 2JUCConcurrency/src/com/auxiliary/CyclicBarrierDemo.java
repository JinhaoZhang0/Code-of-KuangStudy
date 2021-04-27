package com.auxiliary;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    /**
     * 集齐7颗龙珠召唤神龙
     */
    public static void main(String[] args) {
        //召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功!");
        });
        for (int i = 1; i <= 7; i++) {
            int temp = i;
            //Lambda 能操作到变量i吗
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
