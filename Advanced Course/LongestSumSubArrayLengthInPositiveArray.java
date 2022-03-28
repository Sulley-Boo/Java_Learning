package com.javatest;

import java.util.HashMap;
import java.util.Map;

public class LongestSumSubArrayLengthInPositiveArray {
    /**
     * 给定一个数组 arr，该数组无序，但每个值均为正数，再给定一个正数k。求arr的
     * 所有子数组中所有元素相加和为 k 的最长子数组长度。
     * 例如，arr=[1,2,1,1,1]，k=3。
     * 累加和为 3 的最长子数组为[1,1,1]，所以结果返回 3。
     * 要求：时间复杂度O(N)，额外空间复杂度O(1)。
     * <p>
     * 附加题：若数组的值有正有负有0，该如何做？
     */
    public static int getMaxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int sum = 0;
        int l = 0;
        int res = 0;
        for (int r = 0; r < arr.length; r++) {
            sum += arr[r];
            while (sum > k) {
                sum -= arr[l++];
            }
            if (sum == k) {
                res = Math.max(res, r - l + 1);
            }
        }
        return res;
    }

    public static int getMaxLength2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static int getMaxLengthInArray(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }


    // for test
    public static int[] generatePositiveArray(int size) {
        int maxSize = (int) (Math.random() * size);
        int[] result = new int[maxSize];
        for (int i = 0; i != maxSize; i++) {
            result[i] = (int) (Math.random() * 100) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int testTime = 100000;
        boolean succeed = true;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len);
            int k = (int) (Math.random() * 50 + 20);
            if (getMaxLength1(arr, k) != getMaxLength2(arr, k)) {
                succeed = false;
            }
        }
        System.out.println(succeed ? "测试成功!" : "测试失败!");

        int[] nums = {1, 0, 2, -1, 1, -4, 5, -1, -2, 3, 1, 1, 9};
        int k = 3;
        System.out.println(getMaxLengthInArray(nums, k));
    }
}
