package com.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTset {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new Thread().start();

        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        // 适配器
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); //结果会被缓存

        Integer o = (Integer) futureTask.get(); //获取 callable 的返回结果, get方法会产生阻塞! 把它放到最后
        // 或者使用异步通信来处理！
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call");
        // 耗时的操作
        return 1024;
    }
}
