package com.aries.eagle.design.pattern.wrapper;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:24
 * @Comment：
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(new ConcreteDecorator1(
                new ConcreteComponent()
        ));

        component.doSomething();
    }
}
