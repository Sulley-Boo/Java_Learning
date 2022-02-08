package com.javatest;


public class Test {
    /**
     * 象棋中马的跳法
     * 【题目】
     * 请同学们自行搜索或者想象一个象棋的棋盘，然后把整个棋盘放入第一象限，棋盘的最左下
     * 角是(0,0)位置。那么整个棋盘就是横坐标上9条线、纵坐标上10条线的一个区域。给你三个
     * 参数，x，y，k，返回如果“马”从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数
     * 有多少种？
     */

    //暴力递归求解
    public static int process1(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        if (step == 0) {
            return (x == 0 && y == 0) ? 1 : 0;
        }
        return process1(x - 2, y - 1, step - 1)
                + process1(x - 1, y - 2, step - 1)
                + process1(x + 2, y - 1, step - 1)
                + process1(x + 1, y - 2, step - 1)
                + process1(x + 2, y + 1, step - 1)
                + process1(x + 1, y + 2, step - 1)
                + process1(x - 2, y + 1, step - 1)
                + process1(x - 1, y + 2, step - 1);
    }

    public static int ways1(int x, int y, int step) {
        return process1(x, y, step);
    }

    //记忆化搜索
    public static int process2(int x, int y, int step, int[][][] dp) {
        if (x < 0 || x > 8 || y < 0 || y > 9) {
            return 0;
        }
        if (dp[x][y][step] != -1) {
            return dp[x][y][step];
        }
        if (step == 0) {
            if (x == 0 && y == 0) {
                dp[x][y][step] = 1;
            } else {
                dp[x][y][step] = 0;
            }
        } else {
            dp[x][y][step] = process2(x - 2, y - 1, step - 1, dp)
                    + process2(x - 1, y - 2, step - 1, dp)
                    + process2(x + 2, y - 1, step - 1, dp)
                    + process2(x + 1, y - 2, step - 1, dp)
                    + process2(x + 2, y + 1, step - 1, dp)
                    + process2(x + 1, y + 2, step - 1, dp)
                    + process2(x - 2, y + 1, step - 1, dp)
                    + process2(x - 1, y + 2, step - 1, dp);
        }
        return dp[x][y][step];
    }

    public static int ways2(int x, int y, int step) {
        int[][][] dp = new int[9][10][step + 1];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < step + 1; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return process2(x, y, step, dp);
    }

    //动态规划法
    public static int ways3(int x, int y, int step) {
        if (x < 0 || x > 8 || y < 0 || y > 9 || step < 0) {
            return 0;
        }
        int[][][] dp = new int[9][10][step + 1];
        dp[0][0][0] = 1;
        for (int h = 1; h <= step; h++) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 10; c++) {
                    dp[r][c][h] += getValue(dp, r - 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r - 2, c + 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c - 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c - 1, h - 1);
                    dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                    dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
                }
            }
        }
        return dp[x][y][step];
    }

    public static int getValue(int[][][] dp, int row, int col, int step) {
        if (row < 0 || row > 8 || col < 0 || col > 9) {
            return 0;
        }
        return dp[row][col][step];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways1(x, y, step));
        System.out.println(ways2(x, y, step));
        System.out.println(ways3(x, y, step));
    }
}
