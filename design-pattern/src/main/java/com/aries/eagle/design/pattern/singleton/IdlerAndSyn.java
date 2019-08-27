package com.aries.eagle.design.pattern.singleton;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-27 06:42
 * 单例模式--懒汉式+同步方法
 */
public class IdlerAndSyn {
    private IdlerAndSyn() {
    }

    private static IdlerAndSyn idlerAndSyn;

    public static synchronized IdlerAndSyn getInstance() {
        if (idlerAndSyn == null) {
            idlerAndSyn = new IdlerAndSyn();
        }
        return idlerAndSyn;
    }

    public static void main(String[] args) {
        IdlerAndSyn idlerAndSyn1 = IdlerAndSyn.getInstance();
        IdlerAndSyn idlerAndSyn2 = IdlerAndSyn.getInstance();

        System.out.println(idlerAndSyn1 == idlerAndSyn2);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 999999999; i++) {
            IdlerAndSyn instance = IdlerAndSyn.getInstance();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
