package com.javatest;

public class SplitNumber {
    /**
     * 给定一个正数1，裂开的方法有一种，(1)
     * 给定一个正数2，裂开的方法有两种，(1、1)、(2)
     * 给定一个正数3，裂开的方法有三种，(1、1、1)、(1、2)、(3)
     * 给定一个正数4，裂开的方法有五种，(1、1、1、1)、(1、1、2)、(1、3)、（2、2）、（4）
     * 给定一个正数n，求裂开的方法数。
     * 动态规划优化状态依赖的技巧(斜率优化)。
     */

    // 方法1：暴力递归
    public static int ways1(int n) {
        if (n < 1) {
            return 0;
        }
        return process1(1, n);
    }

    // 还剩rest需要去裂开
    // 至少要从rest中拆出pre
    public static int process1(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ans = 0;
        for (int k = pre; k <= rest; k++) {
            ans += process1(k, rest - k);
        }
        return ans;
    }

    // 方法2：记忆化搜索
    public static int ways2(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(1, n, dp);
    }

    public static int process2(int pre, int rest, int[][] dp) {
        if (dp[pre][rest] != -1) {
            return dp[pre][rest];
        }
        if (rest == 0) {
            dp[pre][rest] = 1;
        } else if (pre > rest) {
            dp[pre][rest] = 0;
        } else {
            int ans = 0;
            for (int k = pre; k <= rest; k++) {
                ans += process2(k, rest - k, dp);
            }
            dp[pre][rest] = ans;
        }
        return dp[pre][rest];
    }

    // 方法3：动态规划法
    public static int ways3(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int pre = n; pre >= 1; pre--) {
            for (int rest = 1; rest < n + 1; rest++) {
                for (int k = pre; k <= rest; k++) {
                    dp[pre][rest] += dp[k][rest - k];
                }
            }
        }
        return dp[1][n];
    }

    // 方法4：动态规划法(斜率优化)
    public static int ways4(int n) {
        if (n < 1) {
            return 0;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < n + 1; i++) {
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest < n + 1; rest++) {
                dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(ways1(20));
        System.out.println(ways2(20));
        System.out.println(ways3(20));
        System.out.println(ways4(20));
    }
}
