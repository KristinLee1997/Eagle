package com.aries.eagle.java.concurrent.threadcoreknowledge.createthreads.wrongways;

/**
 * @Author: Kristin
 * @Date: 2019-08-24 10:01
 * @Comment： Lambda表达式方式创建线程
 */
public class LamdaDemo {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}

