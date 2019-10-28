package com.aries.eagle.design.pattern.strategy;

public class SubStrategy implements Strategy {
    @Override
    public int calculate(int a, int b) {
        return a - b;
    }
}
