package com.aries.eagle.java.concurrent.threadcoreknowledge.createthreads;

/**
 * 用Thread方式实现多线程
 */
public class ThreadStyle extends Thread {
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }

    public static void main(String[] args) {
        new ThreadStyle().start();
    }
}
