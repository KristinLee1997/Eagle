package com.aries.eagle.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-09-10 14:39
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listener(TestEvent testEvent) {
        lastMessage = testEvent.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    public int getMessage() {
        return lastMessage;
    }
}
