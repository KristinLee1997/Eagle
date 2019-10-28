package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 00:23
 * @Comment：
 */
public class SortTest {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3, 1, 6, 7, 5, 9, 2, 8, 0, 4};
        MergeSort.sort(arr);
        for (Integer i : arr) {
            System.out.print(i);
        }
    }
}
