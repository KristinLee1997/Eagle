package com.aries.eagle.java.concurrent.threadcoreknowledge.stopthreads;

/**
 * @Author: Kristin
 * @Date: 2019-08-26 08:09
 * @Comment： 如果在执行过程中，每次循环都会调用sleep或wait方法，那么不需要每次迭代都检查是否已中断
 */
public class RightWayStopThreadWaitSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);// 当线程正在休眠的过程中，如果线程收到了中断，sleep会以报异常的方式响应中断
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
