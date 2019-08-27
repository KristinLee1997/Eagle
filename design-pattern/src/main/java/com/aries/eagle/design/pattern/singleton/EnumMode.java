package com.aries.eagle.design.pattern.singleton;


/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-27 10:47
 * 单例模式-枚举类
 */
public enum EnumMode {
    INSTANCE;

    private String name;

    private EnumMode() {
        this.name = "kristin";
    }

    public static EnumMode getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 999999999; i++) {
            EnumMode enumMode = EnumMode.getInstance();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
