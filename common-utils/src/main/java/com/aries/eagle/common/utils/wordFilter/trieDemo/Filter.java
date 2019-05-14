package com.aries.eagle.common.utils.wordFilter.trieDemo;

public interface Filter {
    void addWord(String word);

    boolean hasBadWord(String text);

    String replaceWith(String text, char mark);
}