package com.aries.eagle.common.utils.wordFilter.setDemo;

import org.junit.Test;

public class HashFilterTest {
    @Test
    public void test() {
        HashFilter filter = new HashFilter();
        filter.addKey("测试");
        filter.addKey("脏话");
        String res = filter.findOne("这里有脏话吗？");
        System.out.println(res);
    }
}
