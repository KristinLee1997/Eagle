package com.aries.eagle.design.pattern.singleton;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-27 10:37
 * 单例模式-静态内部类 线程安全
 */
public class InnerDemo {
    private static class Lazyholder {
        private static final InnerDemo inner = new InnerDemo();
    }

    private InnerDemo() {
    }

    public static InnerDemo getInstance() {
        return Lazyholder.inner;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 999999999; i++) {
            InnerDemo innerDemo = InnerDemo.getInstance();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
