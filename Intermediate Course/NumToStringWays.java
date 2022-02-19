package com.javatest;

import java.util.Arrays;

public class NumToStringWays {
    /**
     * 将给定的数转换为字符串，原则如下：1对应 a，2对应b，…..26对应z，例如12258
     * 可以转换为"abbeh", "aveh", "abyh", "lbeh" and "lyh"，个数为5，编写一个函
     * 数，给出可以转换的不同字符串的个数。
     */

    //暴力方法求解
    public static int convertWays1(int num) {
        if (num < 1) {
            return 0;
        }
        char[] chs = String.valueOf(num).toCharArray();
        return process1(chs, 0);
    }

    public static int process1(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        }
        if (chs[i] == '0') {
            return 0;
        }
        int res = process1(chs, i + 1);
        if (i == chs.length - 1) {
            return res;
        }
        if ((chs[i] - '0') * 10 + chs[i + 1] - '0' <= 26) {
            res += process1(chs, i + 2);
        }
        return res;
    }

    //记忆化搜索
    public static int process2(char[] chs, int i, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        if (i == chs.length) {
            dp[i] = 1;
        } else if (chs[i] == '0') {
            dp[i] = 0;
        } else {
            int res = process2(chs, i + 1, dp);
            if (i == chs.length - 1) {
                dp[i] = res;
            } else if ((chs[i] - '0') * 10 + chs[i + 1] - '0' <= 26) {
                res += process2(chs, i + 2, dp);
            }
            dp[i] = res;
        }
        return dp[i];
    }

    public static int convertWays2(int num) {
        if (num < 1) {
            return 0;
        }
        char[] chs = String.valueOf(num).toCharArray();
        int[] dp = new int[chs.length + 1];
        Arrays.fill(dp, -1);
        return process2(chs, 0, dp);
    }

    //动态规划法
    public static int convertWays3(int num) {
        if (num < 1) {
            return 0;
        }
        char[] chs = String.valueOf(num).toCharArray();
        int[] dp = new int[chs.length + 1];
        dp[chs.length] = 1;
        dp[chs.length - 1] = chs[chs.length - 1] == '0' ? 0 : 1;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if ((chs[i] - '0') * 10 + chs[i + 1] - '0' <= 26) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int num1 = 12258;
        System.out.println(convertWays1(num1));
        System.out.println(convertWays2(num1));
        System.out.println(convertWays3(num1));

        int num2 = 111143311;
        System.out.println(convertWays1(num2));
        System.out.println(convertWays2(num2));
        System.out.println(convertWays3(num2));

        int testTime = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int num = (int) (Math.random() * Integer.MAX_VALUE);
            if (convertWays1(num) != convertWays3(num)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
