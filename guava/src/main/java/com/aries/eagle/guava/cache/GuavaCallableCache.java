package com.aries.eagle.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-09-10 16:12
 */
public class GuavaCallableCache {
    public static void main(String[] args) {
        final String key = "0101";
        Cache<String, Optional<List<String>>> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener(notification -> System.out.println("cache expired, remove key : " + notification.getKey())).build(); //监听缓存里的key被移除时触发的事件
        try {
            Optional<List<String>> optional;
            System.out.println("load from cache once : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            System.out.println("load from cache twice : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            System.out.println("load from cache third : " + cache.get(key, () -> Optional.ofNullable(MockDB.getCityListFromDB(key))).orElse(null));
            Thread.sleep(2000);
            final String nullKey = "0103";
            optional = cache.get(nullKey, () -> Optional.ofNullable(MockDB.getCityListFromDB(nullKey)));
            System.out.println("load not exist key from cache : " + optional.orElse(null));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}