package com.kristin.eagle.architecture;

import com.aries.eagle.architecture.lock.zk.Callback;
import com.aries.eagle.architecture.lock.zk.ZkDistributedLockTemplate;
import com.aries.eagle.architecture.lock.zookeeper.ZooKeeperDistributedLock;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.jupiter.api.Test;

public class ZookeeperLockTest {
    @Test
    public void test() {
        ZooKeeperDistributedLock zk = new ZooKeeperDistributedLock("ceshi");
        zk.tryLock();
        zk.unlock();
    }

    @Test
    public void test2() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("114.115.132.189:2181", retryPolicy);
        client.start();

        final ZkDistributedLockTemplate template = new ZkDistributedLockTemplate(client);//本类多线程安全,可通过spring注入
        template.execute("订单流水号", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //TODO 获得锁后要做的事
                System.out.println("获取到锁了");
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                //TODO 获得锁超时后要做的事
                System.out.println("获取锁超时了");
                return null;
            }
        });
    }
}
