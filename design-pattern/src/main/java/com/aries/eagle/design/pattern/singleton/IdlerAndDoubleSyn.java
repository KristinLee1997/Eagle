package com.aries.eagle.design.pattern.singleton;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-27 10:12
 * 单例模式--懒汉式+双重校验所
 */
public class IdlerAndDoubleSyn {
    private IdlerAndDoubleSyn() {
    }

    private static IdlerAndDoubleSyn idlerAndDoubleSyn;

    public static IdlerAndDoubleSyn getInstance() {
        if (idlerAndDoubleSyn == null) {
            synchronized (IdlerAndDoubleSyn.class) {
                if (idlerAndDoubleSyn == null) {
                    idlerAndDoubleSyn = new IdlerAndDoubleSyn();
                }
            }
        }
        return idlerAndDoubleSyn;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 999999999; i++) {
            IdlerAndDoubleSyn instance = IdlerAndDoubleSyn.getInstance();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
