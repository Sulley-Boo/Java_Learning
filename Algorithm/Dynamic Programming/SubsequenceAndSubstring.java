package com.javatest;

import java.util.Arrays;

public class SubsequenceAndSubstring {
    /**
     * 动态规划相似题型：
     * 关于字符串：
     * 最长公共子序列问题
     * 最长公共子串问题
     * 最长回文子串问题
     * 最长回文子序列问题
     * 关于数组：
     * 最长递增子序列问题
     * 最长不连续子集问题(连续指的是相邻两个数差的绝对值 <= 1)
     *
     * 附加：如何求最长公共子序列的个数?
     * dp[i][j] 表示s1[0,i]和s2[0,j]最长公共子序列的长度
     * g[i][j] 表示s1[0,i]和s2[0,j]最长公共子序列的个数
     * 在求最长公共子序列的基础上，求g[i][j]
     * 1）s1[i] != s2[j]时：
     * 如果dp[i][j] == dp[i - 1][j]:g[i][j] += g[i - 1][j];
     * 如果dp[i][j] == dp[i][j - 1]:g[i][j] += g[i][j - 1];
     * 如果dp[i][j] == dp[i - 1][j - 1]:g[i][j] -= g[i - 1][j - 1];
     * 2）s1[i] == s2[j]时：
     * 首先g[i][j] = g[i - 1][j - 1];
     * 如果dp[i][j] == dp[i - 1][j]:g[i][j] += g[i - 1][j];
     * 如果dp[i][j] == dp[i][j - 1]:g[i][j] += g[i][j - 1];
     * 最后返回g[m - 1][n - 1].
     */

    /*==========最长公共子序列==========*/
    public static int LCSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }
    
    // 动态规划，空间压缩
    public static int LCSubsequence1(String s1, String s2) {
        int m = Math.min(s1.length(), s2.length());
        int n = m == s1.length() ? s2.length() : s1.length();
        String small = m == s1.length() ? s1 : s2;
        String big = small.equals(s1) ? s2 : s1;
        int[] dp = new int[m];
        int tmp1 = 0;
        int tmp2 = 0;
        dp[0] = small.charAt(0) == big.charAt(0) ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[i] = small.charAt(i) == big.charAt(0) ? 1 : dp[i - 1];
        }
        for (int i = 1; i < n; i++) {
            tmp1 = dp[0]; // 之后可能还会用到dp[0]，记录下来
            dp[0] = small.charAt(0) == big.charAt(i) ? 1 : dp[0];
            for (int j = 1; j < m; j++) {
                tmp2 = dp[j]; // 之后可能还会用到dp[j]，记录下来
                if (big.charAt(i) != small.charAt(j)) {
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                } else {
                    dp[j] = tmp1 + 1;
                }
                tmp1 = tmp2;
            }
        }
        return dp[m - 1];
    }

    /*==========最长公共子串==========*/
    public static int LCSubstring1(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int res = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    // 动态规划法
    // 仅使用有限个变量进行空间压缩
    // 额外空间复杂度O(1)
    public static int LCSubstring2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int res = 0;
        int row = 0, col = n - 1;
        int len = 0;
        while (row < m) {
            int i = row;
            int j = col;
            len = 0;
            while (i < m && j < n) {
                if (s1.charAt(i) != s2.charAt(j)) {
                    len = 0;
                } else {
                    len++;
                }
                res = Math.max(res, len);
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return res;
    }
    
    /*==========最长回文子串==========*/
    // 解法1：动态规划
    // 解法2：Manacher算法
    public static int LPSubstring(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (j == i + 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                } else {
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] == j - i - 1 ? j - i + 1 : dp[i + 1][j - 1];
                    }
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    /*==========最长回文子序列==========*/
    public static int LPSubsequence(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }

    /*==========最长递增子序列问题==========*/
    // 解法1,时间复杂度O(N^2)
    public static int LISubsequence1(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 解法2
    // 利用动态规划 + 二分查找
    // 时间复杂度O(NlogN)
    public static int LISubsequence2(int[] nums) {
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length];
        Arrays.fill(ends, Integer.MAX_VALUE);
        int res = 1;
        dp[0] = 1;
        ends[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            int index = nearestIndex(ends, nums[i]);
            ends[index] = nums[i];
            dp[i] = index + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int nearestIndex(int[] ends, int value) {
        int index = -1;
        int l = 0;
        int r = ends.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (ends[mid] >= value) {
                index = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    /*==========最长不连续子集问题==========*/
    //美团笔试原题
    public static int longestDiscontinuousSubset(int[] arr) {
        Arrays.sort(arr);
        int[] dp = new int[arr.length];
        dp[0] = 1;
        dp[1] = arr[1] - arr[0] <= 1 ? 1 : 2;
        for (int i = 2; i < dp.length; i++) {
            if (arr[i] - arr[i - 1] > 1) { // 当前数和上一个数不连续，可以要
                dp[i] = dp[i - 1] + 1;
            } else if (arr[i] - arr[i - 1] == 0) { // 当前数和上一个数连续且相等，不能要
                dp[i] = dp[i - 1];
            } else { // 当前数和上一个数连续且差一
                // 两种决策
                // 不要当前数，dp[i - 1]
                // 要当前数，第i-1位上的数不能要，第i-1位往前和arr[i - 1]相等的数都不能要
                int tmp = i - 2;
                while (tmp > 0 && arr[tmp] == arr[tmp + 1]) {
                    tmp--;
                }
                dp[i] = Math.max(dp[tmp] + 1, dp[i - 1]);
            }
        }
        return dp[dp.length - 1];
    }
    
    public static void main(String[] args) {
        String s1 = "abcdefgh";
        String s2 = "acdfhaba";
        System.out.println(LCSubsequence(s1, s2));
        String s3 = "abcdefgh";
        String s4 = "acefgxd";
        System.out.println(LCSubstring(s3, s4));
        String s5 = "12331abcdefedcba121hxd";
        System.out.println(LPSubstring(s5));
        String s6 = "abhcdeefxihiaojkjidhfiedceba";
        System.out.println(LPSubsequence(s6));
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(LISubsequence1(nums));
        System.out.println(LISubsequence2(nums));
    }
}
