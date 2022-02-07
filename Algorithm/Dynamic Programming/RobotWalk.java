package com.javatest;

import java.util.*;

public class Test {
    /**
     * 机K器MP人算达法到扩指展定题位目置二方法数
     * 【题目】
     * 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2。开始时机器人在其中的 M 位
     * 置上(M 一定是 1~N 中的一个)，机器人可以往左走或者往右走，如果机器人来到 1 位置， 那
     * 么下一步只能往右来到 2 位置;如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置。
     * 规定机器人必须走 K 步，最终能来到 P 位置(P 也一定是 1~N 中的一个)的方法有多少种。给
     * 定四个参数 N、M、K、P，返回方法数。
     * 【举例】
     * N=5,M=2,K=3,P=3
     * 上面的参数代表所有位置为 1 2 3 4 5。机器人最开始在 2 位置上，必须经过 3 步，最后到
     * 达 3 位置。走的方法只有如下 3 种: 1)从2到1，从1到2，从2到3 2)从2到3，从3到2，从2到3
     * 3)从2到3，从3到4，从4到3
     * 所以返回方法数 3。 N=3,M=1,K=3,P=3
     * 上面的参数代表所有位置为 1 2 3。机器人最开始在 1 位置上，必须经过 3 步，最后到达 3
     * 位置。怎么走也不可能，所以返回方法数 0
     */
    public static int process1(int N, int P, int rest, int cur) {
        // N:N个位置; P:最终到达P位置; rest:还剩rest步可以走; cur:机器人达到当前位置
        if (rest == 0) {
            return cur == P ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, P, rest - 1, 2);
        }
        if (cur == N) {
            return process1(N, P, rest - 1, N - 1);
        }
        return process1(N, P, rest - 1, cur - 1) + process1(N, P, rest - 1, cur + 1);
    }

    public static int recurWays1(int N, int M, int K, int P) {
        //暴力递归法
        //时间复杂度O(2^K)
        //参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        return process1(N, P, K, M);
    }

    public static int process2(int N, int P, int rest, int cur, int[][] dp) {
        if (dp[rest][cur] != -1) {
            return dp[rest][cur];
        }
        if (rest == 0) {
            dp[rest][cur] = cur == P ? 1 : 0;
        } else if (cur == 1) {
            dp[rest][cur] = process2(N, P, rest - 1, 2, dp);
        } else if (cur == N) {
            dp[rest][cur] = process2(N, P, rest - 1, N - 1, dp);
        } else {
            dp[rest][cur] = process2(N, P, rest - 1, cur - 1, dp) + process2(N, P, rest - 1, cur + 1, dp);
        }
        return dp[rest][cur];
    }

    public static int recurWays2(int N, int M, int K, int P) {
        //带数组缓存的递归法,减少递归计算的次数
        //时间复杂度O(K*N)
        //参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(N, P, K, M, dp);
    }

    public static int dpWays(int N, int M, int K, int P) {
        //动态规划法
        //时间复杂度O(K*N)
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        dp[0][P] = 1;
        for (int rest = 1; rest <= K; ++rest) {
            for (int cur = 1; cur <= N; ++cur) {
                if (cur == 1) {
                    dp[rest][cur] = dp[rest - 1][cur + 1];
                } else if (cur == N) {
                    dp[rest][cur] = dp[rest - 1][cur - 1];
                } else {
                    dp[rest][cur] = dp[rest - 1][cur - 1] + dp[rest - 1][cur + 1];
                }
            }
        }
        return dp[K][M];
    }

    public static int ways4(int N, int M, int K, int P) {
        //另一个动态规划法
        //参数无效直接返回0
        if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        int[] dp = new int[N + 1];
        dp[P] = 1;
        for (int i = 1; i <= K; i++) {
            int leftUp = dp[1];// 左上角的值
            for (int j = 1; j <= N; j++) {
                int tmp = dp[j];
                if (j == 1) {
                    dp[j] = dp[j + 1];
                } else if (j == N) {
                    dp[j] = leftUp;
                } else {
                    dp[j] = leftUp + dp[j + 1];
                }
                leftUp = tmp;
            }
        }
        return dp[M];
    }
    public static void main(String[] args) {
        System.out.println(recurWays1(7, 4, 9, 5));
        System.out.println(recurWays2(7, 4, 9, 5));
        System.out.println(dpWays(7, 4, 9, 5));
        System.out.println(ways4(7, 4, 9, 5));
    }
}
