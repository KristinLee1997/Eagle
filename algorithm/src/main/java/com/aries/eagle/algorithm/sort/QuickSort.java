package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 01:19
 * @Comment：
 */
public class QuickSort {
    private static Integer partation(Integer[] arr, Integer l, Integer r) {
        Integer i = l, j = r;
        Integer temp = arr[l];
        while (i < j) {
            while (i < j && temp < arr[j]) {
                j--;
            }
            arr[i] = arr[j];
            while (i < j && temp > arr[i]) {
                i++;
            }
            arr[j] = arr[i];
        }
        arr[j] = temp;
        return j;
    }

    private static void sort(Integer[] arr, Integer l, Integer r) {
        if (r <= l) {
            return;
        }
        int pos = partation(arr, l, r);
        sort(arr, l, pos - 1);
        sort(arr, pos + 1, r);
    }

    public static void sort(Integer[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
