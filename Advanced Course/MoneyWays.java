package com.javatest;

public class MoneyWays {


    public static int moneyWays(int[] common, int[] souvenir, int m) {
        int[][] dp1 = new int[common.length + 1][m + 1];
        int[][] dp2 = new int[souvenir.length + 1][m + 1];
        // 完全背包问题
        dp1[common.length][0] = 1;
        for (int index = common.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < m + 1; rest++) {
                dp1[index][rest] = dp1[index + 1][rest];
                if (rest - common[index] >= 0) {
                    dp1[index][rest] += dp1[index][rest - common[index]];
                }
            }
        }
        // 01背包问题
        dp2[souvenir.length][0] = 1;
        for (int index = souvenir.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < m + 1; rest++) {
                dp2[index][rest] = dp2[index + 1][rest];
                if (rest - souvenir[index] >= 0) {
                    dp2[index][rest] += dp2[index + 1][rest - souvenir[index]];
                }
            }
        }
        int ways = 0;
        for (int i = 0; i <= m; i++) {
            ways += dp1[0][i] * dp2[0][m - i];
        }
        return ways;
    }
}
