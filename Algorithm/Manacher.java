package com.javatest;

public class Manacher {
    /**
     * Manacher算法实现
     * 字符串str中，最长回文子串的长度如何求解？
     * 如何做到时间复杂度O(N)完成？
     * <p>
     * R：记录当前最大回文右边界
     * C：记录当前最大回文子串的中心点
     * (1) 当前位置i > R:暴力扩展
     * (2) 当前位置i <= R：
     * 1) i的对称点i'的回文区域在L...R内：i的回文区域与i'一样大
     * 2) i的对称点i'的回文区域左边界在L...R外：i的回文半径为R - i
     * 3) i的对称点i'的回文区域左边界刚好在L上：i的回文半径至少是R - i，有可能更大，需要接着往外扩展
     */

    public static char[] manacherString(String str) {
        //将字符串转化为可处理的带'#'的字符数组
        char[] charArr = str.toCharArray();
        char[] res = new char[2 * charArr.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length]; //回文半径数组
        int C = -1; //中心
        int R = -1; //回文右边界的再往右一个位置，最右的有效区域是R-1位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pArr.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1; //当前位置至少不用验的区域
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) { //能往外扩的情况下，往外扩展
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) { //C和R的更新
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1; //字符串处理后的最大回文半径 = 原字符串最大回文子串长度 + 1
    }

    //方法二：使用动态规划法求解
    //暴力递归求解
    public static int process1(String str, int i, int j) {
        if (i == j) { //base case
            return 1;
        }
        if (j == i + 1) { //base case
            return str.charAt(i) == str.charAt(j) ? 2 : 1;
        }
        if (str.charAt(i) != str.charAt(j)) {
            return Math.max(process1(str, i + 1, j), process1(str, i, j - 1));
        }
        return process1(str, i + 1, j - 1) == j - i - 1 ? j - i + 1 : process1(str, i + 1, j - 1);
    }

    public static int ways1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str, 0, str.length() - 1);
    }

    //记忆化搜索
    public static int process2(String str, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (i == j) { //base case
            dp[i][j] = 1;
        } else if (j == i + 1) {
            dp[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 1;
        } else if (str.charAt(i) != str.charAt(j)) {
            dp[i][j] = Math.max(process2(str, i + 1, j, dp), process2(str, i, j - 1, dp));
        } else {
            dp[i][j] = process2(str, i + 1, j - 1, dp) == j - i - 1 ? j - i + 1 : process2(str, i + 1, j - 1, dp);
        }
        return dp[i][j];
    }

    public static int ways2(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < str.length(); j++) {
                dp[i][j] = -1;
            }
        }
        return process2(str, 0, str.length() - 1, dp);
    }

    //动态规划法
    public static int ways3(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for (int col = 0; col < str.length(); col++) {
            dp[col][col] = 1;
            for (int row = col - 1; row >= 0; row--) {
                if (col == row + 1) {
                    dp[row][col] = str.charAt(row) == str.charAt(col) ? 2 : 1;
                } else {
                    if (str.charAt(row) != str.charAt(col)) {
                        dp[row][col] = Math.max(dp[row + 1][col], dp[row][col - 1]);
                    } else {
                        dp[row][col] = dp[row + 1][col - 1] == col - row - 1 ? col - row + 1 : dp[row + 1][col - 1];
                    }
                }
            }
        }
        return dp[0][str.length() - 1];
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
        System.out.println(ways1(str1));
        System.out.println(ways2(str1));
        System.out.println(ways3(str1));
        System.out.println("--------");
        String str2 = "abcbcb1234567654321ad";
        System.out.println(maxLcpsLength(str2));
        System.out.println(ways1(str2));
        System.out.println(ways2(str2));
        System.out.println(ways3(str2));
    }
}
