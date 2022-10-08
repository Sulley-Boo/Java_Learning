import java.util.*;

public class NLengthMValueLIS3 {
    /**
     * 来自微众银行笔试，2020年9月13日
     * 给定一个数字n，代表数组的长度。
     * 给定一个数字m，代表数组每个位置都可以在1~m之间选择数字。
     * 所有长度为n的数组中，最长递增子序列长度为3的数组，叫做达标数组。
     * 返回达标数组的数量，结果对998244353取模。
     * 1 <= n <= 500
     * 1 <= m <= 10
     */
    public static final int mod = 998244353;

    // 方法1：暴力递归
    public static int ways1(int n, int m) {
        return process1(0, 0, 0, 0, m, n);
    }

    public static int process1(int i, int f, int s, int t, int m, int n) {
        if (i == n) {
            return t != 0 ? 1 : 0;
        }
        int ans = 0;
        for (int num = 1; num <= m; num++) {
            if (f == 0 || f >= num) {
                ans = (ans + process1(i + 1, num, s, t, m, n)) % mod;
            } else if (s == 0 || s >= num) {
                ans = (ans + process1(i + 1, f, num, t, m, n)) % mod;
            } else if (t == 0 || t >= num) {
                ans = (ans + process1(i + 1, f, s, num, m, n)) % mod;
            } else {
                break;
            }
        }
        return ans;
    }

    // 记忆化搜索
    public static int ways2(int n, int m) {
        int[][][][] dp = new int[n + 1][m + 1][m + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < m + 1; k++) {
                    for (int l = 0; l < m + 1; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
        return process2(0, 0, 0, 0, m, n, dp);
    }

    public static int process2(int i, int f, int s, int t, int m, int n, int[][][][] dp) {
        if (dp[i][f][s][t] != -1) {
            return dp[i][f][s][t];
        }
        if (i == n) {
            dp[i][f][s][t] = t != 0 ? 1 : 0;
        } else {
            int ans = 0;
            for (int num = 1; num <= m; num++) {
                if (f == 0 || f >= num) {
                    ans = (ans + process2(i + 1, num, s, t, m, n, dp)) % mod;
                } else if (s == 0 || s >= num) {
                    ans = (ans + process2(i + 1, f, num, t, m, n, dp)) % mod;
                } else if (t == 0 || t >= num) {
                    ans = (ans + process2(i + 1, f, s, num, m, n, dp)) % mod;
                } else {
                    break;
                }
            }
            dp[i][f][s][t] = ans;
        }
        return dp[i][f][s][t];
    }

    // 暴力方法，用于测试代码正确性
    public static int ways3(int n, int m) {
        return dfs(0, n, m, new int[n]);
    }

    public static int dfs(int i, int n, int m, int[] path) {
        if (i == n) {
            return lengthOfLIS(path) == 3 ? 1 : 0;
        } else {
            int ans = 0;
            for (int cur = 1; cur <= m; cur++) {
                path[i] = cur;
                ans = (ans + dfs(i + 1, n, m, path)) % mod;
            }
            return ans;
        }
    }

    public static int lengthOfLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        int right = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            max = Math.max(max, l + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("功能测试开始...");
        boolean flag = true;
        for (int n = 3; n <= 12; n++) {
            for (int m = 1; m <= 5; m++) {
                int ans1 = ways1(n, m);
                int ans2 = ways2(n, m);
                int ans3 = ways3(n, m);
                if (ans1 != ans2 || ans2 != ans3) {
                    System.out.println(ans1);
                    System.out.println(ans2);
                    System.out.println(ans3);
                    flag = false;
                    break;
                }
            }
        }
        System.out.println(flag ? "测试成功!" : "测试失败!");
        System.out.println("功能测试结束...");

        System.out.println("性能测试开始");
        int n = 2000;
        int m = 20;
        System.out.println("序列长度 : " + n);
        System.out.println("数字范围 : " + m);
        long start = System.currentTimeMillis();
        System.out.println(ways2(n, m));
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }
}
