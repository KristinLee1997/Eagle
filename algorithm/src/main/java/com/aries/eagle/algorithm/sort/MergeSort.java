package com.aries.eagle.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: Kristin
 * @Date: 2019/10/14 01:54
 * @Comment：
 */
public class MergeSort {
    private static void merge(Integer[] arr, Integer l, Integer mid, Integer r) {
        Integer[] aux = Arrays.copyOfRange(arr, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }

    private static void sort(Integer[] arr, Integer l, Integer r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        sort(arr, l, mid );
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    public static void sort(Integer[] arr) {
        sort(arr, 0, arr.length - 1);
    }
}
