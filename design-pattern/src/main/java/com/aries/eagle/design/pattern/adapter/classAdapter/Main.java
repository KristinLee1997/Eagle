package com.aries.eagle.design.pattern.adapter.classAdapter;

public class Main {
    public static void main(String[] args) {
        Print print = new PrintBanner("hello");
        print.printStrong();
        print.printWeak();
    }
}
