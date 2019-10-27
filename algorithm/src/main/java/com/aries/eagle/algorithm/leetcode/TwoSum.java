package com.aries.eagle.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kristin
 * @Date: 2019/10/20 15:54
 * @Comment： Leetcode 1：找出数组中和为target的两个数
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer temp = target - nums[i];
            if (map.containsKey(temp)) {
                return new int[]{map.get(temp), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] arr = new TwoSum().twoSum(nums, 9);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
