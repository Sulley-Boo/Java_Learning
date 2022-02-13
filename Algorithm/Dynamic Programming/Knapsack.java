package com.javatest;

public class Knapsack {
    /**
     * 01背包问题
     * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
     * i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物
     * 品不能超过这个重量。返回你能装下最多的价值是多少？
     */

    //暴力递归求解
    //前面0 ~ i-1号物品已做好决策
    //对第i号物品做决策
    //两种决策，选择或者不选择
    //在做选择的决策时，先要判断加入i号物品后是否超重
    public static int process1(int[] weights, int[] values, int i, int curWeight, int bag) {
        if (i == weights.length) {
            return 0;
        }
        int notSelect = process1(weights, values, i + 1, curWeight, bag);
        int select = 0;
        if (curWeight + weights[i] <= bag) {
            select = values[i] + process1(weights, values, i + 1, curWeight + weights[i], bag);
        }
        return Math.max(notSelect, select);
    }

    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process1(weights, values, 0, 0, bag);
    }

    //记忆化搜索
    public static int process2(int[] weights, int[] values, int i, int curWeight, int bag, int[][] dp) {
        if (dp[i][curWeight] != -1) {
            return dp[i][curWeight];
        }
        if (i == weights.length) {
            dp[i][curWeight] = 0;
        } else {
            int notSelect = process2(weights, values, i + 1, curWeight, bag, dp);
            int select = 0;
            if (curWeight + weights[i] <= bag) {
                select = values[i] + process2(weights, values, i + 1, curWeight + weights[i], bag, dp);
            }
            dp[i][curWeight] = Math.max(notSelect, select);
        }
        return dp[i][curWeight];
    }

    public static int maxValue2(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int i = 0; i < weights.length + 1; i++) {
            for (int j = 0; j < bag + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(weights, values, 0, 0, bag, dp);
    }

    //动态规划法
    public static int maxValue3(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int curWeight = 0; curWeight < bag + 1; curWeight++) {
            dp[weights.length][curWeight] = 0;
        }
        for (int i = weights.length - 1; i >= 0; i--) {
            for (int curWeight = 0; curWeight < bag + 1; curWeight++) {
                dp[i][curWeight] = dp[i + 1][curWeight];
                if (curWeight + weights[i] <= bag) {
                    dp[i][curWeight] = Math.max(dp[i][curWeight], values[i] + dp[i + 1][curWeight + weights[i]]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] weights1 = {3, 2, 4, 7};
        int[] values1 = {5, 6, 3, 19};
        int bag1 = 11;
        System.out.println(maxValue1(weights1, values1, bag1));
        System.out.println(maxValue2(weights1, values1, bag1));
        System.out.println(maxValue3(weights1, values1, bag1));
        System.out.println("========");
        int[] weights2 = {1, 2, 3, 2};
        int[] values2 = {4, 6, 12, 7};
        int bag2 = 6;
        System.out.println(maxValue1(weights2, values2, bag2));
        System.out.println(maxValue2(weights2, values2, bag2));
        System.out.println(maxValue3(weights2, values2, bag2));
    }
}
