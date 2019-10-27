package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 00:14
 * @Comment：
 */
public class BubbleSort {
    public void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int flag = 1;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.swap(arr, j, j + 1);
                }
            }
            if (flag == 0) {
                return;
            }
        }
    }
}
