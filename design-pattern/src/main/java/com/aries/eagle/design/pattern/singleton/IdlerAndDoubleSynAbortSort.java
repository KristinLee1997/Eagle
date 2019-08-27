package com.aries.eagle.design.pattern.singleton;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-27 10:27
 * 单例模式--懒汉式+双重校验锁+防止指令重排
 */
public class IdlerAndDoubleSynAbortSort {
    private IdlerAndDoubleSynAbortSort() {
    }

    private static volatile IdlerAndDoubleSynAbortSort idlerAndDoubleSynAbortSort;

    public static IdlerAndDoubleSynAbortSort getInstance() {
        if (idlerAndDoubleSynAbortSort == null) {
            synchronized (IdlerAndDoubleSynAbortSort.class) {
                if (idlerAndDoubleSynAbortSort == null) {
                    idlerAndDoubleSynAbortSort = new IdlerAndDoubleSynAbortSort();
                }
            }
        }
        return idlerAndDoubleSynAbortSort;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 999999999; i++) {
            IdlerAndDoubleSynAbortSort idlerAndDoubleSynAbortSort = new IdlerAndDoubleSynAbortSort();
        }

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
