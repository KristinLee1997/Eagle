package com.aries.eagle.design.pattern.wrapper;

/**
 * @Author: Kristin
 * @Date: 2019/12/2 23:25
 * @Comment：
 */
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }

    private void doAnotherThing() {
        System.out.println("功能C");
    }
}
