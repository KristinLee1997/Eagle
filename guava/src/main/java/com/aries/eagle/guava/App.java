package com.aries.eagle.guava;


import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/**
 *
 */
public class App {
    public static void main(String[] args) {
//        String bucket = "0,1,2,3,4,5,6,7,8,9";
//        int size = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(bucket).size();
//        System.out.println(size/1000.0);
        List list = new ArrayList();
        list.add(1);
        Set set = new HashSet<>(list);
        System.out.println(set);
    }
}

class A implements RandomAccess{

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}