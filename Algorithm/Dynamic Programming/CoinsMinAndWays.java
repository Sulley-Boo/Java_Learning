package com.javatest;

public class CoinsMinAndWays {
    /**
     * 换钱的最少货币数
     * 【题目】
     * 给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值
     * 的货币可以使用任意张，再给定一个整数 aim，代表要找的钱数，求组成 aim 的最少货币
     * 数。
     * 【举例】
     * arr=[5,2,3]，aim=20。
     * 4 张 5 元可以组成 20 元，其他的找钱方案都要使用更多张的货币，所以返回 4。
     * arr=[5,2,3]，aim=0。
     * 不用任何货币就可以组成 0 元，返回 0。
     * arr=[3,5]，aim=2。
     * 根本无法组成 2 元，钱不能找开的情况下默认返回-1。
     * <p>
     * 附加题：换钱的方法数
     * 组成目标钱数的方法有多少种？
     */

    /*====================CoinsMin====================*/
    //暴力递归求解
    public static int coinsMin1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }
        return process3(arr, 0, aim);
    }

    // 当前考虑的面值是arr[i]，还剩rest的钱需要找零
    // 如果返回-1说明自由使用arr[i..N-1]面值的情况下，无论如何也无法找零rest
    // 如果返回不是-1，代表自由使用arr[i..N-1]面值的情况下，找零rest需要的最少张数
    public static int process3(int[] arr, int i, int rest) {
        // base case：
        // 已经没有面值能够考虑了
        // 如果此时剩余的钱为0，返回0张
        // 如果此时剩余的钱不是0，返回-1
        if (i == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        // 最少张数，初始时为-1
        int res = -1;
        // 依次尝试使用当前面值(arr[i])0张、1张、k张，但不能超过rest
        for (int k = 0; arr[i] * k <= rest; k++) {
            // 使用了k张arr[i]，剩下的钱为rest - k * arr[i]
            // 交给剩下的面值去搞定(arr[i+1..N-1])
            int next = process3(arr, i + 1, rest - arr[i] * k);
            if (next != -1) { // 说明这个后续过程有效
                res = res == -1 ? next + k : Math.min(res, next + k);
            }
        }
        return res;
    }

    //记忆化搜索
    public static int coinsMin2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = -2;
            }
        }
        return process4(arr, 0, aim, dp);
    }

    public static int process4(int[] arr, int i, int rest, int[][] dp) {
        if (dp[i][rest] != -2) {
            return dp[i][rest];
        }
        if (i == arr.length) {
            dp[i][rest] = rest == 0 ? 0 : -1;
        } else {
            int res = -1;
            for (int k = 0; arr[i] * k <= rest; k++) {
                int next = process4(arr, i + 1, rest - arr[i] * k, dp);
                if (next != -1) {
                    res = res == -1 ? next + k : Math.min(res, next + k);
                }
            }
            dp[i][rest] = res;
        }
        return dp[i][rest];
    }

    //动态规划法
    public static int coinsMin3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 1; i < aim + 1; i++) {
            dp[arr.length][i] = -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                int res = -1;
                for (int k = 0; arr[i] * k <= rest; k++) {
                    int next = dp[i + 1][rest - arr[i] * k];
                    if (next != -1) {
                        res = res == -1 ? next + k : Math.min(res, next + k);
                    }
                }
                dp[i][rest] = res;
            }
        }
        return dp[0][aim];
    }

    //动态规划法优化版本（斜率优化）
    public static int coinsMin4(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 1; i < aim + 1; i++) {
            dp[arr.length][i] = -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                //dp[i][rest]依赖它下面的值dp[i + 1][rest]和它左边的值dp[i][rest - arr[i]]
                dp[i][rest] = -1;// 初始时先设置dp[i][rest]的值无效
                if (dp[i + 1][rest] != -1) { // 下面的值如果有效
                    dp[i][rest] = dp[i + 1][rest];// dp[i][rest]的值先设置成下面的值
                }
                // 左边的位置不越界并且有效
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    if (dp[i][rest] == -1) { // 如果之前下面的值无效
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else { // 说明下面和左边的值都有效，取最小的
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    /*====================CoinsWays====================*/
    //暴力递归求解
    public static int coinsWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    //可以自由使用arr[index...]所有的面值
    //需要搞定的钱数是rest
    //返回找零的方法数
    public static int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //arr[index] 0张 1张 ... 不要超过rest的钱数
        int ways = 0;
        for (int k = 0; arr[index] * k <= rest; k++) {
            ways += process1(arr, index + 1, rest - arr[index] * k);
        }
        return ways;
    }

    //记忆化搜索
    public static int process2(int[] arr, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
        } else {
            int ways = 0;
            for (int k = 0; arr[index] * k <= rest; k++) {
                ways += process2(arr, index + 1, rest - arr[index] * k, dp);
            }
            dp[index][rest] = ways;
        }
        return dp[index][rest];
    }

    public static int coinsWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < aim + 1; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, aim, dp);
    }

    //动态规划法
    //时间复杂度O(N*aim*aim)
    public static int coinsWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                for (int k = 0; arr[index] * k <= rest; k++) {
                    dp[index][rest] += dp[index + 1][rest - arr[index] * k];
                }
            }
        }
        return dp[0][aim];
    }

    //动态规划法优化版本（斜率优化）
    //时间复杂度O(N*aim)
    public static int coinsWays4(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < aim + 1; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int[] generateRandomArray(int len, int max) {
        int[] arr = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 3};
        int aim = 20;
        System.out.println(coinsWays1(arr, aim));
        System.out.println(coinsWays2(arr, aim));
        System.out.println(coinsWays3(arr, aim));
        System.out.println(coinsWays4(arr, aim));
        System.out.println("========");
        System.out.println(coinsMin1(arr, aim));
        System.out.println(coinsMin2(arr, aim));
        System.out.println(coinsMin3(arr, aim));
        System.out.println(coinsMin4(arr, aim));
        System.out.println("========");
        int len = 10;
        int max = 10;
        int testTime = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(len, max);
            int aim1 = (int) (Math.random() * 3 * max) + max;
            if (coinsMin1(arr1, aim1) != coinsMin2(arr1, aim1) ||
                    coinsMin2(arr1, aim1) != coinsMin3(arr1, aim1) ||
                    coinsMin3(arr1, aim1) != coinsMin4(arr1, aim1)) {
                System.out.println("测试失败!");
            }
            if (coinsWays1(arr1, aim1) != coinsWays2(arr1, aim1) ||
                    coinsWays2(arr1, aim1) != coinsWays3(arr1, aim1) ||
                    coinsWays3(arr1, aim1) != coinsWays4(arr1, aim1)) {
                System.out.println("测试失败!");
            }
        }
    }
}
