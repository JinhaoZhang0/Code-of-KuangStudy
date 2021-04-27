package com.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * 求和计算
 * 如何使用ForkJoin
 * 1. 使用ForkJoinPool 通过它来执行
 * 2. 计算任务， forkjoinpool.execute(ForkJoinTask task)
 * 3. 计算类要继承forkjointask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;

    private long temp = 10000L;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end-start) < temp){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            // forkjoin
            long middle = (start + end)/2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(middle+1, end);
            task2.fork();

            return task1.join()+task2.join();
        }
    }
}
