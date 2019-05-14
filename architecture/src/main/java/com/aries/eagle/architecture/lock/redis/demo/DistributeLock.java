package com.aries.eagle.architecture.lock.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

public class DistributeLock {
    private static final JedisPool pool = JedisConfig.getJedisPool();

    public static String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        String retIdertifier = null;
        try (Jedis conn = pool.getResource()) {
            String lockKey = "lock:" + lockName;
            String identifier = UUID.randomUUID().toString();
            int lockExpire = (int) timeout / 1000;
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
                if (conn.setnx(lockKey, identifier) == 1) {
                    conn.expire(lockKey, lockExpire);
                    retIdertifier = identifier;
                    return retIdertifier;
                }
                if (conn.ttl(lockKey) == -1) {
                    conn.expire(lockKey, lockExpire);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retIdertifier;
    }

    public static boolean releaseLock(String lockName, String idertifier) {
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try (Jedis conn = pool.getResource()) {
            while (true) {
                conn.watch(lockKey);
                if (idertifier.equals(conn.get(lockKey))) {
                    Transaction transaction = conn.multi();
                    transaction.del(lockKey);
                    List<Object> exec = transaction.exec();
                    if (exec == null) {
                        continue;
                    }
                    retFlag = true;
                }
                conn.unwatch();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retFlag;
    }
}
