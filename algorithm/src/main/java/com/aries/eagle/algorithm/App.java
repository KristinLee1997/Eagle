package com.aries.eagle.algorithm;

public class App {
    public static void main(String[] args) {
        // 01&11 = 01
        // 100&011 = 0
        // 10&11=10
        // 10= 1010
        // 9 = 1001
        // 8 = 1000
        int c = String.valueOf(1034).charAt(4 - 1) - '0';
        System.out.println();
    }

    public Integer test(Integer n) {
        int res = 0;
        while (n >= 1) {
            System.out.println("res: " + res);
            System.out.println("n1: " + n);
            n = n & (n - 1);
            System.out.println("n2: " + n);
            res++;
        }
        return res;
    }
}

