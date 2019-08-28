package com.aries.eagle.java.concurrent.threadcoreknowledge.startways;

/**
 * @Author: Kristin
 * @Date: 2019-08-24 10:20
 * @Comment： 对比start和run两种方式
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run(); // sout:main

        new Thread(runnable).start(); // sout: Thread-0
    }
}
