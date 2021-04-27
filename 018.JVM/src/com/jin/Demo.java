package com.jin;

public class Demo {
    public static void main(String[] args) {
        new Thread(()->{

        },"my thread namne").start();
    }

    /**
     * native : 凡是带了native关键字的，说明java的作用范围达不到了，回去调用底层C语言的库！
     *     会进入本地方法栈
     *     调用本地方法接口 JNI
     *     JNI作用：扩展Java的使用，融合不同的编程语言为Java所用！最初：C, C++
     *     Java诞生的时候C, C++横行，想要立足，必须要能调用C,C++的程序
     *     它在内存区域中专门开辟类一块标记区域：Native Method Stack，登记native方法
     *     在最终执行的时候，加载本地方法库中的方法通过JNI
     */
    // Java程序驱动打印机，管理系统，掌握即可，在企业级应用中较为少见
    private native void start0();

    //调用其他接口： Socket, WebService~ http~
}
