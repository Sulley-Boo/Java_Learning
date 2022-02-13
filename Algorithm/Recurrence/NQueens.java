package com.javatest;

import java.util.*;

public class NQueens {
    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，
     * 也不在同一条斜线上。 给定一个整数n，返回n皇后的摆法有多少种。
     * n=1，返回1。 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
     * n=8，返回92。
     */

    public static int process(int[] record, int i, int n) {
        if (i == n) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                ans += process(record, i + 1, n);
            }
        }
        return ans;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 14;
        int[] record = new int[n];
        System.out.println(process(record, 0, n));
    }
}
