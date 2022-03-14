package com.javatest;

public class SubMatrixMaxSum {
    /**
     * 给定一个整型矩阵，返回子矩阵的最大累计和。
     */
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int[] help = null;
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++) {
            help = new int[m[0].length];
            for (int j = i; j < m.length; j++) {
                cur = 0;
                for (int k = 0; k < help.length; k++) {
                    help[k] += m[j][k];
                    cur += help[k];
                    max = Math.max(max, cur);
                    cur = Math.max(0, cur);
                }
            }
        }
        return max;
    }

    public static int[][] generateMatrix(int rowSize, int colSize) {
        int[][] m = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                m[i][j] = (int) (Math.random() * 500 - 250);
            }
        }
        return m;
    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};
        System.out.println(maxSum(matrix));
        int[][] m = generateMatrix(10, 20);
        printMatrix(m);
        System.out.println(maxSum(m));
    }
}
