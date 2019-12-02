package com.aries.eagle.design.pattern.wrapper;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:21
 * @Comment：
 */
public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能B");
    }
}
