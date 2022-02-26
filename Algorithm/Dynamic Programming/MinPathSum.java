package com.javatest;

public class MinPathSum {
    /**
     * 矩阵的最小路径和
     * 【题目】
     * 给定一个矩阵 m，从左上角开始每次只能向右或者向下走，最后到达右下角的位置，路径
     * 上所有的数字累加起来就是路径和，返回所有的路径中最小的路径和。
     * 【举例】
     * 如果给定的 m 如下: 1359 8134 5061 8840
     * 路径 1，3，1，0，6，1，0 是所有路径中路径和最小的，所以返回12
     */

    //暴力递归求解
    public static int minPathSum1(int[][] m) {
        return process1(m, 0, 0);
    }

    public static int process1(int[][] m, int i, int j) {
        if (i == m.length - 1 && j == m[0].length - 1) {
            //base case
            //如果从最右下角的位置出发，返回该位置的代价
            return m[m.length - 1][m[0].length - 1];
        }
        if (i == m.length - 1) {
            //当前位置在最下一行，只能往右走
            return m[i][j] + process1(m, i, j + 1);
        }
        if (j == m[0].length - 1) {
            //当前位置在最右一列，只能往下走
            return m[i][j] + process1(m, i + 1, j);
        }
        //往右走或者往下走，返回代价小的那一个
        return m[i][j] + Math.min(process1(m, i + 1, j), process1(m, i, j + 1));
    }

    //记忆化搜索
    public static int minPathSum2(int[][] m) {
        int[][] dp = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(m, 0, 0, dp);
    }

    public static int process2(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == m.length - 1 && j == m[0].length - 1) {
            dp[i][j] = m[m.length - 1][m[0].length - 1];
        } else if (i == m.length - 1) {
            dp[i][j] = m[i][j] + process2(m, i, j + 1, dp);
        } else if (j == m[0].length - 1) {
            dp[i][j] = m[i][j] + process2(m, i + 1, j, dp);
        } else {
            dp[i][j] = m[i][j] + Math.min(process2(m, i + 1, j, dp), process2(m, i, j + 1, dp));
        }
        return dp[i][j];
    }

    //动态规划法
    public static int minPathSum3(int[][] m) {
        int[][] dp = new int[m.length][m[0].length];
        dp[m.length - 1][m[0].length - 1] = m[m.length - 1][m[0].length - 1];
        for (int j = m[0].length - 2;j >= 0;j--) {
            dp[m.length - 1][j] = m[m.length - 1][j] + dp[m.length - 1][j + 1];
        }
        for (int i = m.length - 2;i >= 0;i--) {
            dp[i][m[0].length - 1] = m[i][m[0].length - 1] + dp[i + 1][m[0].length - 1];
        }
        for (int i = m.length - 2;i >= 0;i--) {
            for (int j = m[0].length - 2;j >= 0;j--) {
                dp[i][j] = m[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    //优化版本
    public static int minPathSum4(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;
        int[] arr = new int[less];
        arr[0] = m[0][0];
        for (int i = 1; i < less; i++) {
            arr[i] = arr[i - 1] + (rowmore ? m[0][i] : m[i][0]);
        }
        for (int i = 1; i < more; i++) {
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            for (int j = 1; j < less; j++) {
                arr[j] = Math.min(arr[j - 1], arr[j])
                        + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less - 1];
    }

    //动态规划法优化（空间压缩）
    public static int minPathSum5(int[][] m) {
        int[] dp = new int[m[0].length];
        dp[m[0].length - 1] = m[m.length - 1][m[0].length - 1];
        for (int j = m[0].length - 2; j >= 0;j--) {
            dp[j] = m[m.length - 1][j] + dp[j + 1];
        }
        for (int i = m.length - 2;i >= 0;i--) {
            for (int j = m[0].length - 1;j >= 0;j--) {
                if (j == m[0].length - 1) {
                    dp[j] = dp[j] + m[i][j];
                } else {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + m[i][j];
                }
            }
        }
        return dp[0];
    }

    // for test
    public static int[][] generateRandomMatrix(int maxRowSize, int maxColSize) {
        if (maxRowSize < 0 || maxColSize < 0) {
            return null;
        }
        int rowSize = (int) (Math.random() * maxRowSize + 1);
        int colSize = (int) (Math.random() * maxColSize + 1);
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = {{1, 3, 5, 9}, {8, 1, 3, 4}, {5, 0, 6, 1}, {8, 8, 4, 0}};
        printMatrix(m);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
        System.out.println(minPathSum3(m));
        System.out.println(minPathSum4(m));
        System.out.println(minPathSum5(m));
        
        int[][] m1 = generateRandomMatrix(20, 20);
        int testTime = 10000;
        System.out.println("测试进行中...");
        for (int i = 0;i < testTime;i++)  {
            if (minPathSum1(m1) != minPathSum2(m1) ||
                    minPathSum2(m1) != minPathSum3(m1) ||
                    minPathSum3(m1) != minPathSum4(m1) ||
                    minPathSum4(m1) != minPathSum5(m1)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
