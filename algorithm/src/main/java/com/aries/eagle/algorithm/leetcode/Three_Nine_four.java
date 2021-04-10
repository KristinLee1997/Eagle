package com.aries.eagle.algorithm.leetcode;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @Author: Kristin
 * @Date: 2021/4/10 下午11:10
 * @Comment：
 */
public class Three_Nine_four {
    String src;
    int ptr;

    // 正向递归
    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }

    // 反向递归
    public String decodeString2(String s) {
        // 第一个]的位置
        int end = s.indexOf("]");
        if (end == -1) {
            return s;
        }

        // 第一个[的位置
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
        // 需要遍历的次数
        Long times = Long.parseLong(s.substring(numberIndex, start));

        // 拼接重复字符串
        for (int i = 0; i < times; i++) {
            String aa = s.substring(start + 1, end);
            sss = sss + aa;
        }
        String newStr = s.substring(0, numberIndex) + sss + s.substring(end + 1);
        return decodeString(newStr);
    }

    public static void main(String[] args) {
        String s = "3[ab]";
        System.out.println(new Three_Nine_four().decodeString(s));
        System.out.println(s.indexOf("8"));
    }
}

class Three_Nine_four2 {
    int ptr;

    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[bc]]";
        System.out.println(new Three_Nine_four2().decodeString(s));
        System.out.println(s.indexOf("8"));

    }
}

