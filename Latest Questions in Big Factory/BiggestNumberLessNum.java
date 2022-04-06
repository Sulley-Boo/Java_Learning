package com.javatest;

import java.util.*;

public class BiggestNumberLessNum {

    // 字节面试题
    public static int ans = -1;
    public static void dfs(int[] arr, int i, int sum, int n, int num) {
        if (i == n) {
            if (sum < num) {
                ans = Math.max(ans, sum);
            }
            return;
        }
        for (int k = 0; k < arr.length; k++) {
            if (sum * 10 + arr[k] > num) {
                return;
            }
            dfs(arr, i + 1, sum * 10 + arr[k], n, num);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 9};
        Arrays.sort(arr);
        int num = 4921;
        int n = String.valueOf(num).length();
        dfs(arr, 0, 0, n, num);
        if (ans == -1) {
            ans = 0;
            for (int i = 0; i < n - 1; i++) {
                ans = ans * 10 + arr[arr.length - 1];
            }
        }
        System.out.println(ans);
    }
}
