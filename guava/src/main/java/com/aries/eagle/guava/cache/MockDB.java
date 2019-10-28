package com.aries.eagle.guava.cache;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;

/**
 * @author lihang17
 * @version 1.0
 * @date 2019-09-10 16:12
 */
public class MockDB {
    public static List<String> getCityListFromDB(String cityId) {
        System.out.println("getting from DB,cityId:" + cityId + " please wait...");
        List<String> returnList = null;
        // 模仿从数据库中取数据
        try {
            switch (cityId) {
                case "0101":
                    returnList = ImmutableList.of("上海", "北京", "广州", "深圳");
                    break;
            }
        } catch (Exception e) {
            // 记日志
        }
        return Optional.fromNullable(returnList).or(Collections.EMPTY_LIST);
    }
}