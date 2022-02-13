package com.javatest;


public class BobDie {
    /**
     * Bob的生存概率
     * 【题目】
     * 给定五个参数n,m,i,j,k。表示在一个N*M的区域，Bob处在(i,j)点，每次Bob等概率的向上、
     * 下、左、右四个方向移动一步，Bob必须走K步。如果走完之后，Bob还停留在这个区域上，
     * 就算Bob存活，否则就算Bob死亡。请求解Bob的生存概率，返回字符串表示分数的方式。
     */

    //求最大公约数
    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    //方法1：暴力递归求解
    public static int process1(int n, int m, int i, int j, int k) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }
        return process1(n, m, i - 1, j, k - 1) +
                process1(n, m, i + 1, j, k - 1) +
                process1(n, m, i, j - 1, k - 1) +
                process1(n, m, i, j + 1, k - 1);
    }

    public static String ways1(int N, int M, int i, int j, int K) {
        long all = (long) Math.pow(4, K);
        long live = process1(N, M, i, j, K);
        long gcd = gcd(all, live);
        return (live / gcd) + "/" + (all / gcd);
    }

    //方法2：记忆化搜索
    public static int process2(int n, int m, int i, int j, int k, int[][][] dp) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }
        if (dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        if (k == 0) {
            dp[i][j][k] = 1;
        } else {
            dp[i][j][k] = process2(n, m, i - 1, j, k - 1, dp) +
                    process2(n, m, i + 1, j, k - 1, dp) +
                    process2(n, m, i, j - 1, k - 1, dp) +
                    process2(n, m, i, j + 1, k - 1, dp);
        }
        return dp[i][j][k];
    }

    public static String ways2(int N, int M, int i, int j, int K) {
        int[][][] dp = new int[N][M][K + 1];
        for (int r = 0; r < N; r++) {
            for (int l = 0; l < M; l++) {
                for (int h = 0; h < K + 1; h++) {
                    dp[r][l][h] = -1;
                }
            }
        }
        long all = (long) Math.pow(4, K);
        long live = process2(N, M, i, j, K, dp);
        long gcd = gcd(all, live);
        return (live / gcd) + "/" + (all / gcd);
    }

    public static int getValue(int[][][] dp, int N, int M, int i, int j, int k) {
        if (i < 0 || i >= N || j < 0 || j >= M || k < 0) {
            return 0;
        }
        return dp[i][j][k];
    }
    
    //方法3：动态规划法
    public static String ways3(int N, int M, int i, int j, int K) {
        int[][][] dp = new int[N][M][K + 1];
        for (int r = 0; r < N; r++) {
            for (int l = 0; l < M; l++) {
                dp[r][l][0] = 1;
            }
        }
        for (int h = 1; h <= K; h++) {
            for (int r = 0; r < N; r++) {
                for (int l = 0; l < M; l++) {
                    dp[r][l][h] = getValue(dp, N, M, r - 1, l, h - 1) +
                            getValue(dp, N, M, r + 1, l, h - 1) +
                            getValue(dp, N, M, r, l - 1, h - 1) +
                            getValue(dp, N, M, r, l + 1, h - 1);
                }
            }
        }
        long all = (long) Math.pow(4, K);
        long live = dp[i][j][K];
        long gcd = gcd(all, live);
        return (live / gcd) + "/" + (all / gcd);
    }

    public static void main(String[] args) {
        int N = 10;
        int M = 10;
        int i = 3;
        int j = 2;
        int K = 5;
        System.out.println(ways1(N, M, i, j, K));
        System.out.println(ways2(N, M, i, j, K));
        System.out.println(ways3(N, M, i, j, K));
    }
}
