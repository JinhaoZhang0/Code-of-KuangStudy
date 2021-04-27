package com.function;

import java.util.function.Function;

/**
 * Function 函数型接口,有一个输入参数，有一个输出参数，有一个输出
 * 只要是 函数型接口 可以用 lambda表达式简化
 */
public class Demo01Function {
    public static void main(String[] args) {

        // 工具类： 输出输入的值
//        Function function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        }; //1
//        Function<String, String> function = (str) -> {return str;}; //2
        Function<String, String> function = str -> str; //3




        System.out.println(function.apply("asd"));
    }
}
