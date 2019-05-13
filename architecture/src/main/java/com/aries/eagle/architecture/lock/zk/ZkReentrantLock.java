package com.aries.eagle.architecture.lock.zk;

import com.aries.eagle.architecture.lock.zk.DistributedReentrantLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ZkReentrantLock implements DistributedReentrantLock {
    /**
     * 线程池
     */
    private static final ScheduledExecutorService executorservice = Executors.newScheduledThreadPool(10);

    /**
     * 所有PERSISTENT锁节点的根位置
     */
    public static final String ROOT_PATH = "/ROOT_LOCK/";

    /**
     * 每次延迟清理PERSISTENT节点的时间  Unit:MILLISECONDS
     */
    private long delayTimeForClean = 1000;

    /**
     * zk 共享锁实现
     */
    private InterProcessMutex interProcessMutex = null;

    /**
     * 锁的ID,对应zk一个PERSISTENT节点,下挂EPHEMERAL节点.
     */
    private String path;

    /**
     * Zookeeper客户端
     */
    private CuratorFramework zkClient;

    public ZkReentrantLock(CuratorFramework zkClient, String lockId) {
        init(zkClient, lockId);
    }

    public void init(CuratorFramework zkClient, String lockId) {
        this.zkClient = zkClient;
        this.path = ROOT_PATH + lockId;
        this.interProcessMutex = new InterProcessMutex(zkClient, this.path);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit timeUnit) {
        try {
            return interProcessMutex.acquire(timeout, timeUnit);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void unlock() {
        try {
            interProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorservice.schedule(new Cleaner(zkClient, path), delayTimeForClean, TimeUnit.MILLISECONDS);
        }
    }

    static class Cleaner implements Runnable {

        CuratorFramework client;

        String path;

        public Cleaner(CuratorFramework client, String path) {
            this.client = client;
            this.path = path;
        }

        @Override
        public void run() {
            try {
                List<String> list = client.getChildren().forPath(path);
                if (list == null || list.isEmpty()) {
                    client.delete().forPath(path);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
