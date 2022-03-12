package com.javatest;

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

    /*==========最长公共子串==========*/
    public static int LCSubstring(String s1, String s2) {
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
    }
}
