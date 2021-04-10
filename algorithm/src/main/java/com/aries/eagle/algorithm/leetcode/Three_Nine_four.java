package com.aries.eagle.algorithm.leetcode;

/**
 * @Author: Kristin
 * @Date: 2021/4/10 下午11:10
 * @Comment：
 */
public class Three_Nine_four {
    public String decodeString(String s) {
        int end = s.indexOf("]");
        if (end == -1) {
            return s;
        }
        int start = end;
        for (int j = 0; j < end; j++) {
            if (s.charAt(j) == '[') {
                start = j;
            }
        }
        String sss = "";

        int numberIndex = 0;
        for (int i = start - 1; i >= 0; i--) {
            if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
                numberIndex = i;
            } else {
                break;
            }
        }
        Long times = Long.parseLong(s.substring(numberIndex, start));
        for (int i = 0; i < times; i++) {
            String aa = s.substring(start + 1, end);
            sss = sss + aa;
        }
        String newStr = s.substring(0, numberIndex) + sss + s.substring(end + 1);
        return decodeString(newStr);
    }

    public static void main(String[] args) {
        String s = "10[leee]";
        System.out.println(new Three_Nine_four().decodeString(s));
    }
}
