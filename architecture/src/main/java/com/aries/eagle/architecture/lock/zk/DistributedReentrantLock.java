package com.aries.eagle.architecture.lock.zk;

import java.util.concurrent.TimeUnit;

public interface DistributedReentrantLock {
    boolean tryLock(long timeout, TimeUnit timeUnit);

    void unlock();
}
