package com.aries.eagle.architecture.lock.zk;

public interface Callback {

    public Object onGetLock() throws InterruptedException;

    public Object onTimeout() throws InterruptedException;
}
