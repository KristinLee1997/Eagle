package com.aries.eagle.java.concurrent.threadcoreknowledge.stopthreads;

/**
 * @Author: Kristin
 * @Date: 2019-08-26 07:54
 * @Comment： 普通情况下停止线程：run方法内没有sleep或wait方法时，停止线程
 */
public class RightWayStopThreaddWithoutSleep implements Runnable {

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("线程运行结束了");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreaddWithoutSleep());
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
