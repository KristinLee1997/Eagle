package com.aries.eagle.design.pattern.iterator.base;


public interface Aggregate {
    /**
     * 生成遍历集合的迭代器
     *
     * @return
     */
    public abstract Iterator iterator();
}
