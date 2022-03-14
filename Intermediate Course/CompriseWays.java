package com.javatest;

public class CompriseWays {
    /**
     * 牛牛准备参加学校组织的春游, 出发前牛牛准备往背包里装入一些零食, 牛牛的背包容
     * 量为w。
     * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]。
     * 牛牛想知道在总体积不超过背包容量的情况下，他一共有多少种零食放法(总体积为0也
     * 算一种放法)。
     */
    public static int ways1(int[] arr, int w) {
        return process1(arr, 0, w);
    }

    public static int process1(int[] v, int i, int rest) {
        if (i == v.length) {
            return rest >= 0 ? 1 : 0;
        }
        int res = process1(v, i + 1, rest);
        if (rest - v[i] >= 0) {
            res += process1(v, i + 1, rest - v[i]);
        }
        return res;
    }

    public static int process2(int[] v, int i, int rest, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        if (i == v.length) {
            dp[i][rest] = rest >= 0 ? 1 : 0;
        } else {
            int res = process2(v, i + 1, rest, dp);
            if (rest - v[i] >= 0) {
                res += process2(v, i + 1, rest - v[i], dp);
            }
            dp[i][rest] = res;
        }
        return dp[i][rest];
    }

    public static int ways2(int[] arr, int w) {
        int[][] dp = new int[arr.length + 1][w + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, w, dp);
    }

    public static int dpways(int[] arr, int w) {
        int[][] dp = new int[arr.length + 1][w + 1];
        for (int rest = 0; rest < w + 1; rest++) {
            dp[arr.length][rest] = 1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int rest = 0; rest < w + 1; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0) {
                    dp[i][rest] += dp[i + 1][rest - arr[i]];
                }
            }
        }
        return dp[0][w];
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 9};
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));
        System.out.println(dpways(arr, w));
    }
}
