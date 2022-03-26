package com.javatest;

import java.util.HashMap;

public class MostEOR {
    /**
     * 给出n个数字 a_1,...,a_n，问最多有多少不重叠的非空区间，使得每个区间内数字的
     * xor都等于0。
     */

    // 动态规划法
    public static int mostEOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0] == 0 ? 1 : 0;
        int xor = 0;
        HashMap<Integer, Integer> map = new HashMap<>(); // 记录异或值出现的位置
        map.put(0, -1);
        int res = dp[0];
        for (int i = 0; i < dp.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                dp[i] = map.get(xor) >= 0 ? dp[map.get(xor)] + 1 : 1;
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(xor, i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // for test
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eors = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            eors[i] = eor;
        }
        int[] mosts = new int[arr.length];
        mosts[0] = arr[0] == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            mosts[i] = eors[i] == 0 ? 1 : 0;
            for (int j = 0; j < i; j++) {
                if ((eors[i] ^ eors[j]) == 0) {
                    mosts[i] = Math.max(mosts[i], mosts[j] + 1);
                }
            }
            mosts[i] = Math.max(mosts[i], mosts[i - 1]);
        }
        return mosts[mosts.length - 1];
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 300;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res = mostEOR(arr);
            int comp = comparator(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "测试成功!" : "测试失败!");
    }
}
