package com.aries.eagle.design.pattern.iterator.base;

/**
 * 遍历集合中的元素
 */
public interface Iterator {
    /**
     * 判断元素是否存在
     *
     * @return
     */
    public abstract boolean hasNext();

    /**
     * 获取下一个元素
     *
     * @return
     */
    public abstract Object next();
}
