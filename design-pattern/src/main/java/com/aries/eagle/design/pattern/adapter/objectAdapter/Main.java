package com.aries.eagle.design.pattern.adapter.objectAdapter;

public class Main {
    public static void main(String[] args) {
        Print print = new PrintBanner("hi");
        print.printWeak();
        print.printStrong();
    }
}
