package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 00:38
 * @Comment：
 */
public class SelectionSort {
    public static void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortUtils.swap(arr, minIndex, i);
        }
    }
}
