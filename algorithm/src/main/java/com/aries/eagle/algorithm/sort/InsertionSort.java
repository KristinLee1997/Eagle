package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 00:45
 * @Comment：
 */
public class InsertionSort {
    public static void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Integer temp = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }
}
