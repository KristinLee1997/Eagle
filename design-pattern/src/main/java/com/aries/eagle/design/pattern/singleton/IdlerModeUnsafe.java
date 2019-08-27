package com.aries.eagle.design.pattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-26 19:35
 * 单例模式--懒汉式 验证一下懒汉式线程不安全
 */
public class IdlerModeUnsafe {
    private IdlerModeUnsafe() {
    }

    private static IdlerModeUnsafe idlerModeUnsafe;

    public static IdlerModeUnsafe getInstance() {
        if (idlerModeUnsafe == null) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            idlerModeUnsafe = new IdlerModeUnsafe();
        }
        return idlerModeUnsafe;
    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<IdlerModeUnsafe> singletons = new LinkedBlockingQueue<>();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                singletons.offer(IdlerModeUnsafe.getInstance());
            });
        }


        IdlerModeUnsafe basic = singletons.take();
        while (basic == singletons.take()) {
            System.out.println("continue");
            continue;
        }

        System.out.println("走到这里说明单例失败");
    }
}
