package com.pool;

import java.util.concurrent.*;

// Execotors 工具类，三大方法
// 本质 ThreadPoolExecutor()
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadpool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadpool =Executors.newFixedThreadPool(5);  //创建一个固定的线程池的大小
//        ExecutorService threadpool = Executors.newCachedThreadPool();    //可伸缩的，遇强则强，遇弱则弱
        /**
         * 底层使用的都是 ThreadPoolExecutor()，建议直接用其创建
         */

        // 自定义线程池

        // 最大线程到底该如何定义
        // 1. CPU 密集型 几核就是几，可以保持CPU的效率最高
        // 2. IO 密集型  大于  判断你程序中十分耗IO的线程
        //       程序 15个大型任务  io十分占用资源！
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()); //银行满了（候客区），但是还有人进来，不处理这个人的，并抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy()); //哪来的去哪里！
//                new ThreadPoolExecutor.DiscardPolicy()); //队列满了，丢掉任务，但是不会抛出异常
                new ThreadPoolExecutor.DiscardOldestPolicy()); //队列满了，试探最早的，已结束就跟进，未结束则不处理，也不会抛出异常

        try {
            for (int i = 1; i <= 9; i++) {
                // 使用线程池创建线程
                threadpool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadpool.shutdown();
        }


    }
}
