package com.aries.eagle.guava.eventbus;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-09-10 14:37
 */
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:" + message);
    }

    public int getMessage() {
        return message;
    }
}
