package com.aries.eagle.design.pattern.proxy.cglib_proxy;

/**
 * @author Kristin
 * @since 2018/7/27 18:42
 **/
public class UserDaoImpl implements UserDao {
    public void add() {
        System.out.println("the method is running");
    }
}
