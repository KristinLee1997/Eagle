package com.aries.eagle.design.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-08-26 16:13
 * 单例模式--饿汉式【线程安全】【缺点】：类加载时就初始化，浪费内存，容易产生垃圾对象。
 */
public class StarveMode {
    private static final StarveMode starveMode = new StarveMode();

    private StarveMode() {

    }

    public static StarveMode getInstance() {
        return starveMode;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StarveMode starve1 = StarveMode.getInstance();
        StarveMode starve2 = StarveMode.getInstance();
        System.out.println(starve1 == starve2);

        // 反射获取
        StarveMode starve3 = StarveMode.getInstance();
        Constructor constructor = StarveMode.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        StarveMode starve4 = (StarveMode) constructor.newInstance();
        System.out.println(starve3 == starve4);

    }
}
