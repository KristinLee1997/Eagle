package com.aries.eagle.common.utils.wordFilter.trieDemo;

import java.util.HashSet;

public class FastFilter implements Filter {
    private HashSet<String> hash = new HashSet<String>();
    private byte[] fastCheck = new byte[65536];
    private byte[] fastLength = new byte[65536];
    private boolean[] charCheck = new boolean[65536];
    private boolean[] endCheck = new boolean[65536];

    private int maxWordLength = 0;
    private int minWordLength = Integer.MAX_VALUE;

    public void addWord(String word) {
        maxWordLength = Math.max(maxWordLength, word.length());
        minWordLength = Math.min(minWordLength, word.length());

        for (int i = 0; i < 7 && i < word.length(); i++) {
            fastCheck[word.charAt(i)] |= (byte) (1 << i);
        }
        for (int i = 7; i < word.length(); i++) {
            fastCheck[word.charAt(i)] |= 0x80;
        }

        if (word.length() == 1) {
            charCheck[word.charAt(0)] = true;
        } else {
            fastLength[word.charAt(0)] |= (byte) (1 << (Math.min(7, word.length() - 2)));
            endCheck[word.charAt(word.length() - 1)] = true;
            hash.add(word);
        }
    }

    public boolean hasBadWord(String text) {
        int index = 0;
        while (index < text.length()) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < text.length() - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                return true;
            }
            for (int j = 1; j <= Math.min(maxWordLength, text.length() - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hash.contains(text.substring(index, index + j + 1))) {
                            return true;
                        }
                    }
                }
            }
            index += count;
        }
        return false;
    }

    @Override
    public String replaceWith(String text, char mark) {
        int index = 0;
        char[] ca = text.toCharArray();
        while (index < text.length()) {
            int count = 1;
            if (index > 0 || (fastCheck[text.charAt(index)] & 1) == 0) {
                while (index < text.length() - 1 && (fastCheck[text.charAt(++index)] & 1) == 0) ;
            }
            char begin = text.charAt(index);
            if (minWordLength == 1 && charCheck[begin]) {
                ca[index] = mark;
                index++;
                continue;
            }
            for (int j = 1; j <= Math.min(maxWordLength, text.length() - index - 1); j++) {
                char current = text.charAt(index + j);
                if ((fastCheck[current] & 1) == 0) {
                    ++count;
                }
                if ((fastCheck[current] & (1 << Math.min(j, 7))) == 0) {
                    break;
                }
                if (j + 1 >= minWordLength) {
                    if ((fastLength[begin] & (1 << Math.min(j - 1, 7))) > 0 && endCheck[current]) {
                        if (hash.contains(text.substring(index, index + j + 1))) {
                            for (int m = index; m < (index + j + 1); m++) {
                                ca[m] = mark;
                            }
                            break;
                        }
                    }
                }
            }
            index += count;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : ca) {
            sb.append(c);
        }
        return sb.toString();
    }
}
