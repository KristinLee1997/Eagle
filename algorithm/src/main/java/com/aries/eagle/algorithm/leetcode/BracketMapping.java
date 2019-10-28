package com.aries.eagle.algorithm.leetcode;

import java.util.Stack;

/**
 * @Author: Kristin
 * @Date: 2019/10/28 23:52
 * @Comment： Leetcode 20:有效的括号
 */
public class BracketMapping {
    Stack<String> stack = new Stack<>();

    public boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            if ("(".equals(String.valueOf(s.charAt(i)))) {
                stack.push(")");
            }

            if ("{".equals(String.valueOf(s.charAt(i)))) {
                stack.push("}");
            }

            if ("[".equals(String.valueOf(s.charAt(i)))) {
                stack.push("]");
            }

            if (")".equals(String.valueOf(s.charAt(i)))
                    || "}".equals(String.valueOf(s.charAt(i)))
                    || "]".equals(String.valueOf(s.charAt(i)))) {
                if (!stack.isEmpty()) {
                    String pop = stack.pop();
                    if (!pop.equals(String.valueOf(s.charAt(i)))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String sss = "()";
        System.out.println(new BracketMapping().isValid(sss));
    }
}
