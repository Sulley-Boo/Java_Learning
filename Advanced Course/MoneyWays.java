package com.javatest;

public class MoneyWays {

    /**
    * 现有n1+n2种面值的硬币，其中前n1种为普通币，可以取任意枚，后n2种为纪念币，
    * 每种最多只能取一枚，每种硬币有一个面值，问能用多少种方法拼出m的面值？
    */
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
