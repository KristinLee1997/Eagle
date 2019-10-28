package com.aries.eagle.algorithm.sort;

/**
 * @Author: Kristin
 * @Date: 2019/10/13 00:27
 * @Comment：
 */
public class SortUtils {
    public static void swap(Integer[] arr, Integer i, Integer j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{5, 9, 7, 6, 3, 1};
        SortUtils.swap(arr, 0, 5);
        for (Integer i : arr) {
            System.out.println(i);
        }
    }
}
