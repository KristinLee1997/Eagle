package com.aries.eagle.design.pattern.wrapper;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:20
 * @Comment：
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
