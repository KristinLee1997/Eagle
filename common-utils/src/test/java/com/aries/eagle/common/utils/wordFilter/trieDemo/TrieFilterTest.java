package com.aries.eagle.common.utils.wordFilter.trieDemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrieFilterTest {
    @Test
    public void trieFilterTest() {
        List<String> keywords = new ArrayList<String>();
        keywords.add("傻×");
        keywords.add("汉奸");
        keywords.add("草");
        keywords.add("草泥马");
        keywords.add("fuck");
        KeywordFilter.saveKeywords(1, keywords);
        String txt = "是傻×汉奸傻A傻B傻Cfuck傻D汉奸傻×草泥马";
        List<String> list = KeywordFilter.repword(1, txt);
        System.out.println("文中包含的敏感字为:" + list.get(1));
        System.out.println("原文:" + txt);
        System.out.println("敏感字过滤后:" + list.get(0));
    }

    @Test
    public void fastFilterTest() {
        FastFilter filter = new FastFilter();
        filter.addWord("脏词啊");
        boolean res = filter.hasBadWord("这句话里没有脏词吗？");
        System.out.println(res);
    }
}
