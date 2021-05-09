package com.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

// 道高一尺，魔高一丈
// 懒汉式单例
public class Lazy {

    private static boolean jin = false;

    private Lazy(){
        //防止反射破坏
        synchronized (Lazy.class){
            if (jin == false){
                jin = true;
            } else {
                throw new RuntimeException("不要试图使用反射破坏异常");
            }

//            //不能防止初始两次反射破坏
//            if (lazy != null){
//                throw new RuntimeException("不要试图使用反射破坏异常");
//            }
        }
    }

    // 确保不会有指令重排
    private volatile static Lazy lazy;

    // 双重检测锁模式的 懒汉式单例 DCL 懒汉式
    public static Lazy getInstance(){
        if (lazy==null){
            // 直接锁class
            synchronized (Lazy.class){
                if (lazy==null) {
                    lazy = new Lazy(); //不是原子性操作
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3. 把对象指向这个空间
                     */
                }
            }
        }
        return lazy;
    }

    //反射！
    public static void main(String[] args) throws Exception {
        Field jin = Lazy.class.getDeclaredField("jin");
        jin.setAccessible(true);

//        Lazy instance1 = Lazy.getInstance();
        Constructor<Lazy> declaredConstructor = Lazy.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        Lazy instance1 = declaredConstructor.newInstance();

        jin.set(instance1,false);
        Lazy instance2 = declaredConstructor.newInstance();

        System.out.println(instance1);
        System.out.println(instance2);
    }

    // 单线程下确实单例OK
    // 多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                Lazy.getInstance();
//            }).start();
//        }
//    }
}
