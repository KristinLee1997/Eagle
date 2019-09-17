package com.aries.eagle.guava;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class App {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(5);
        list.add(3);
        list.add(7);
        if(list.contains(1)){
            list.remove(list.indexOf(1));
        }
        System.out.println(list);
    }
}
