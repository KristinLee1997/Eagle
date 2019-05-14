package com.aries.eagle.common.utils.wordFilter.setDemo;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;

/**
 * 敏感词过滤-使用Set集合进行
 * 缺点：
 * 1.当前工信部发放的脏词集合大概几千万条，查询缓慢
 * 2. 每次调用查询方法时都会调用subString方法，创建了许多字符串，性能不高
 */
public class HashFilter {
    // 关键字最大长度
    public int keyMaxLength = 0;

    // 存储脏词的Set集合
    public HashSet<String> keysSet = new HashSet<String>();

    /**
     * 向集合中添加敏感词
     *
     * @param key
     */
    public void addKey(String key) {
        if ((!StringUtils.isBlank(key)) && keysSet.add(key) && key.length() > keyMaxLength) {
            keyMaxLength = key.length();
        }
    }

    /**
     * 查询文本中是否存在敏感词
     *
     * @param text
     * @return
     */
    public String findOne(String text) {
        for (int len = 1; len < text.length(); len++) {
            int end = text.length() - len;
            for (int start = 0; start <= end; start++) {
                String key = text.substring(start, end);
                if (keysSet.contains(key)) {
                    return key;
                }
            }
        }
        return null;
    }
}
