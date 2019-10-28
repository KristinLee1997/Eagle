package com.aries.eagle.design.pattern.proxy.cglib_proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author Kristin
 * @since 2018/7/27 18:35
 **/
public class Main {
    @org.junit.jupiter.api.Test
    public void test() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\code");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDaoImpl.class);
        enhancer.setCallback(new Interceptor());
        UserDao userDao = (UserDao) enhancer.create();
        userDao.add();
    }
}
