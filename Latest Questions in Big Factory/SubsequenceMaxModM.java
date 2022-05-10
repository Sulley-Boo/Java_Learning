package com.javatest;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// 来自阿里
public class SubsequenceMaxModM {
    /**
     * 给定一个非负整数arr，和一个正数m。
     * 返回arr中的所有序列中累加和%m后的最大值。
     * 1) 如果arr中每个数字都不大，怎么做？
     * 2) 如果m的值很小，但是arr的累计和非常大，怎么做？
     * 3) 如果arr的长度很短，且arr中每个数字都很大，m也比较大，怎么做？
     */
    // 方法1：暴力递归
    // 时间复杂度O(2^n)
    public static int max1(int[] arr, int m) {
        Set<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int res = 0;
        for (Integer num : set) {
            res = Math.max(res, num % m);
        }
        return res;
    }

    public static void process(int[] arr, int index, int sum, Set<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, index + 1, sum, set);
        process(arr, index + 1, sum + arr[index], set);
    }

    // 方法2：动态规划
    // 时间复杂度O(n * sum)，适用于数组中每个数都不大的情况
    public static int max2(int[] arr, int m) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // dp[i][j]的含义：在arr数组的0 ~ i中的数中自由选择，是否能拼出j出来
        boolean[][] dp = new boolean[arr.length][sum + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j] || (j - arr[i] >= 0 && dp[i - 1][j - arr[i]]);
            }
        }
        int res = 0;
        for (int j = 0; j < sum + 1; j++) {
            if (dp[arr.length - 1][j]) {
                res = Math.max(res, j % m);
            }
        }
        return res;
    }

    // 方法3：动态规划
    // 时间复杂度O(n * m), 适用于数组的累加和比较小的情况
    public static int max3(int[] arr, int m) {
        // dp[i][j]的含义：自由选择0 ~ i 上的数字，是否能拼出累加和%m等于j
        boolean[][] dp = new boolean[arr.length][m];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                int cur = arr[i] % m;
                if (j >= cur) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - cur];
                } else {
                    dp[i][j] = dp[i][j] || dp[i - 1][m + j - cur];
                }
            }
        }
        for (int j = m - 1; j >= 0; j--) {
            if (dp[arr.length - 1][j]) {
                return j;
            }
        }
        return 0;
    }

    // 方法4：将数组分成两部分，第一部分使用暴力递归求出所有的累加和，将它们%m的结果放入一个有序表，另一部分放入另一个有序表
    // 在第一部分中选出一个数left，在第二部分选出<=m-1-left的最大的数
    // 遍历其中一个有序表，即可得到最大的结果
    // 时间复杂度：获取累加和O(2^n), 在有序表中查找最大值O(m * logm)
    // 适用于arr数组长度短，m很大的情况
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> sortSet1 = new TreeSet<>();
        process2(arr, m, 0, mid, 0, sortSet1);
        TreeSet<Integer> sortSet2 = new TreeSet<>();
        process2(arr, m, mid + 1, arr.length - 1, 0, sortSet2);
        int res = 0;
        for (Integer left : sortSet1) {
            res = Math.max(res, left + sortSet2.floor(m - 1 - left));
        }
        return res;
    }

    public static void process2(int[] arr, int m, int i, int j, int sum, TreeSet<Integer> set) {
        if (i == j + 1) {
            set.add(sum % m);
            return;
        }
        process2(arr, m, i + 1, j, sum, set);
        process2(arr, m, i + 1, j, sum + arr[i], set);
    }

    public static int[] generate(int maxLen, int maxValue) {
        int size = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("测试进行中...");
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generate(20, 50);
            int m = (int) (Math.random() * 200) + 1;
            if (max1(arr, m) != max2(arr, m) || max2(arr, m) != max3(arr, m) || max3(arr, m) != max4(arr, m)) {
                System.out.println("测试失败!");
                printArr(arr);
                System.out.println(m);
                break;
            }
        }
    }
}
