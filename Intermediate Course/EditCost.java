package com.javatest;

public class EditCost {
    /**
     * 给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删
     * 除和替换一个字符的代价，返回将str1编辑成str2的最小代价。
     * 【举例】
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=2
     * 从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2
     * str1="abc"，str2="adc"，ic=5，dc=3，rc=100
     * 从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8
     * str1="abc"，str2="abc"，ic=5，dc=3，rc=2
     * 不用编辑了，本来就是一样的字符串，所以返回0
     * Leetcode中的编辑距离问题。
     */
    // 方法1，暴力递归
    public static int minCost1(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        return process1(chs1, chs2, chs1.length, chs2.length, ic, dc, rc);
    }

    public static int process1(char[] chs1, char[] chs2, int i, int j, int ic, int dc, int rc) {
        if (i == 0) {
            return j * ic;
        }
        if (j == 0) {
            return i * dc;
        }
        int res = process1(chs1, chs2, i - 1, j - 1, ic, dc, rc) + (chs1[i - 1] == chs2[j - 1] ? 0 : rc);
        res = Math.min(res, Math.min(process1(chs1, chs2, i - 1, j, ic, dc, rc) + dc, process1(chs1, chs2, i, j - 1, ic, dc, rc) + ic));
        return res;
    }

    // 记忆化搜索
    public static int minCost2(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int[][] dp = new int[chs1.length + 1][chs2.length + 1];
        for (int i = 0; i <= chs1.length; i++) {
            for (int j = 0; j <= chs2.length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(chs1, chs2, chs1.length, chs2.length, ic, dc, rc, dp);
    }

    public static int process2(char[] chs1, char[] chs2, int i, int j, int ic, int dc, int rc, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == 0) {
            dp[i][j] = j * ic;
        } else if (j == 0) {
            dp[i][j] = i * dc;
        } else {
            int res = process2(chs1, chs2, i - 1, j - 1, ic, dc, rc, dp) + (chs1[i - 1] == chs2[j - 1] ? 0 : rc);
            res = Math.min(res, Math.min(process2(chs1, chs2, i - 1, j, ic, dc, rc, dp) + dc,
                    process2(chs1, chs2, i, j - 1, ic, dc, rc, dp) + ic));
            dp[i][j] = res;
        }

        return dp[i][j];
    }

    // 动态规划法
    public static int minCost3(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        int[][] dp = new int[chs1.length + 1][chs2.length + 1];
        for (int i = 0; i <= chs1.length; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 0; j <= chs2.length; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i <= chs1.length; i++) {
            for (int j = 1; j <= chs2.length; j++) {
                dp[i][j] = dp[i - 1][j - 1] + (chs1[i - 1] == chs2[j - 1] ? 0 : rc);
                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i - 1][j] + dc, dp[i][j - 1] + ic));
            }
        }
        return dp[chs1.length][chs2.length];
    }

    // 动态规划法，状态压缩
    public static int minCost4(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j];
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp;
            }
        }
        return dp[shorts.length];
    }

    // for test
    public static String generateString(int maxSize) {
        int size = (int) (Math.random() * (maxSize + 1));
        String res = "";
        for (int i = 0; i < size; i++) {
            res += (int) (Math.random() * 10);
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "ab12cd3";
        String str2 = "abcdf";
        System.out.println(minCost1(str1, str2, 5, 3, 2));
        System.out.println(minCost2(str1, str2, 5, 3, 2));
        System.out.println(minCost3(str1, str2, 5, 3, 2));
        System.out.println(minCost4(str1, str2, 5, 3, 2));

        str1 = "abcdf";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 3, 2, 4));
        System.out.println(minCost2(str1, str2, 3, 2, 4));
        System.out.println(minCost3(str1, str2, 3, 2, 4));
        System.out.println(minCost4(str1, str2, 3, 2, 4));

        str1 = "";
        str2 = "ab12cd3";
        System.out.println(minCost1(str1, str2, 1, 7, 5));
        System.out.println(minCost2(str1, str2, 1, 7, 5));
        System.out.println(minCost3(str1, str2, 1, 7, 5));
        System.out.println(minCost4(str1, str2, 1, 7, 5));

        str1 = "abcdf";
        str2 = "";
        System.out.println(minCost1(str1, str2, 2, 9, 8));
        System.out.println(minCost2(str1, str2, 2, 9, 8));
        System.out.println(minCost3(str1, str2, 2, 9, 8));
        System.out.println(minCost4(str1, str2, 2, 9, 8));
        int testTime = 1000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            String s1 = generateString(10);
            String s2 = generateString(10);
            int ic = (int) (Math.random() * 5 + 1);
            int dc = (int) (Math.random() * 3 + 1);
            int rc = (int) (Math.random() * 8 + 1);
            if (minCost1(s1, s2, ic, dc, rc) != minCost2(s1, s2, ic, dc, rc) ||
                    minCost2(s1, s2, ic, dc, rc) != minCost3(s1, s2, ic, dc, rc) ||
                    minCost3(s1, s2, ic, dc, rc) != minCost4(s1, s2, ic, dc, rc)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
