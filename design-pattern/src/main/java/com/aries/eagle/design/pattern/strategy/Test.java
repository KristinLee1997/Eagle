package com.aries.eagle.design.pattern.strategy;

public class Test {
    public static void main(String[] args) {
        int addRes = StrategyFactory.strategy(1, 8, 6);
        System.out.println(addRes);

        int subRes = StrategyFactory.strategy(2, 8, 6);
        System.out.println(subRes);

    }
}
