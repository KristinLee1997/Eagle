package com.aries.eagle.design.pattern.wrapper;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:19
 * @Comment： 具体构建角色
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
