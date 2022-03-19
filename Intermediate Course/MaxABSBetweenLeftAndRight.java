package com.javatest;

public class MaxABSBetweenLeftAndRight {
    /**
     * 给定一个数组arr长度为N，你可以把任意长度大于0且小于N的前缀作为左部分，剩下的
     * 作为右部分。但是每种划分下都有左部分的最大值和右部分的最大值，请返回最大的，
     * 左部分最大值减去右部分最大值的绝对值。
     */
    // 方法1:暴力枚举
    // 时间复杂度O(n^2),空间复杂度O(1)
    public static int maxABS1(int[] arr) {
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j <= i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }
            int maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j < arr.length; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }
            res = Math.max(res, Math.abs(maxLeft - maxRight));
        }
        return res;
    }

    // 方法2:预处理数组
    // 时间复杂度O(n),空间复杂度O(n)
    public static int maxABS2(int[] arr) {
        int[] leftMax = new int[arr.length];
        int[] rightMax = new int[arr.length];
        leftMax[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        rightMax[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            res = Math.max(res, Math.abs(leftMax[i] - rightMax[i + 1]));
        }
        return res;
    }

    // 方法3:假设数组中的最大值分配在左侧
    // 右侧只有一个数时，两边最大值之差最大
    // 因为将划分边界向左移动时，要么最大值之差不变，要么减小，并不会使结果增大
    // 时间复杂度O(n),空间复杂度O(1)
    public static int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

    // for test
    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7, 5, 9, 2, 10};
        System.out.println(maxABS1(arr));
        System.out.println(maxABS2(arr));
        System.out.println(maxABS3(arr));
        int testTime = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] nums = generateRandomArray(200);
            if (maxABS1(nums) != maxABS2(nums) || maxABS2(nums) != maxABS3(nums)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
