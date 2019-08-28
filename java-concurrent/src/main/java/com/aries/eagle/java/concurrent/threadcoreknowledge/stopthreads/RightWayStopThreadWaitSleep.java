package com.aries.eagle.java.concurrent.threadcoreknowledge.stopthreads;

/**
 * @Author: Kristin
 * @Date: 2019-08-26 08:01
 * @Comment： 带有sleep的中断线程的写法
 */
public class RightWayStopThreadWaitSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
            }
            try {
                Thread.sleep(1000);// 当线程正在休眠的过程中，如果线程收到了中断，sleep会以报异常的方式响应中断
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
