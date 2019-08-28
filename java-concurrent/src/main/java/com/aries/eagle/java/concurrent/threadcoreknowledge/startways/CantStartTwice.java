package com.aries.eagle.java.concurrent.threadcoreknowledge.startways;

/**
 * @Author: Kristin
 * @Date: 2019-08-24 10:27
 * @Comment： 演示不能两次调用start方法
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
