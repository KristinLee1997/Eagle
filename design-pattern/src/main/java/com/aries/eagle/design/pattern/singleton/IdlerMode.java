package com.aries.eagle.design.pattern.singleton;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-26 16:25
 * 单例模式--懒汉模式
 */
public class IdlerMode {
    private IdlerMode() {

    }

    private static IdlerMode idlerMode;

    public static IdlerMode getInstance() {
        if (idlerMode == null) {
            idlerMode = new IdlerMode();
        }
        return idlerMode;
    }

    public static void main(String[] args) {
        IdlerMode idlerMode1 = IdlerMode.getInstance();
        IdlerMode idlerMode2 = IdlerMode.getInstance();
        System.out.println(idlerMode1 == idlerMode2);
    }
}
