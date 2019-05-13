package com.aries.eagle.architecture.lock.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkDistributedLockTemplate implements DistributedLockTemplate {
    private CuratorFramework client;

    public ZkDistributedLockTemplate(CuratorFramework client) {
        this.client = client;
    }

    private boolean tryLock(ZkReentrantLock zkReentrantLock, Long timeout) {
        return zkReentrantLock.tryLock(timeout, TimeUnit.MILLISECONDS);
    }

    @Override
    public Object execute(String lockId, int timeout, Callback callback) {
        ZkReentrantLock zkReentrantLock = null;
        boolean getLock = false;

        try {
            zkReentrantLock = new ZkReentrantLock(client, lockId);
            if (tryLock(zkReentrantLock, new Long(timeout))) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (getLock) {
                zkReentrantLock.unlock();
            }
        }
        return null;
    }
}
