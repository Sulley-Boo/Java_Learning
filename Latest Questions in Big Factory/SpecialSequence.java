import java.util.*;

public class SpecialSequence {

    /**
     * 2022年8月31日，度小满测开笔试第三题
     * 给定n，求长度为n的序列中，1不能相邻且不能隔一位，序列中只能包含1 ~ 9,
     * 求有多少种不同的满足条件的序列。
     * n的规模为100000，答案对1000000007取模。
     */
    public static final int mod = 1000000007;

    public static int process(int n, int i, int pre, int prePre, int[][][] dp) {
        if (dp[i][pre][prePre] != -1) {
            return dp[i][pre][prePre];
        }
        if (i == n) {
            dp[i][pre][prePre] = 1;
        } else {
            int ans = 0;
            if (i == 0) {
                for (int j = 1; j <= 9; j++) {
                    ans = (ans + process(n, i + 1, j, 0, dp)) % mod;
                }
            } else if (i == 1) {
                if (pre == 1) {
                    for (int j = 2; j <= 9; j++) {
                        ans = (ans + process(n, i + 1, j, pre, dp)) % mod;
                    }
                } else {
                    for (int j = 1; j <= 9; j++) {
                        ans = (ans + process(n, i + 1, j, pre, dp)) % mod;
                    }
                }
            } else {
                if ((pre == 1 && prePre != 1) || prePre == 1 && pre != 1) {
                    for (int j = 2; j <= 9; j++) {
                        ans = (ans + process(n, i + 1, j, pre, dp)) % mod;
                    }
                } else {
                    for (int j = 1; j <= 9; j++) {
                        ans = (ans + process(n, i + 1, j, pre, dp)) % mod;
                    }
                }
            }
            dp[i][pre][prePre] = ans;
        }
        return dp[i][pre][prePre];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][][] dp = new int[n + 1][10][10];
        int[][][] dp2 = new int[n + 1][10][10];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    dp2[i][j][k] = -1;
                }
            }
        }
        for (int pre = 0; pre < 10; pre++) {
            for (int prePre = 0; prePre < 10; prePre++) {
                dp[n][pre][prePre] = 1;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                for (int pre = 0; pre < 10; pre++) {
                    for (int prePre = 0; prePre < 10; prePre++) {
                        for (int j = 1; j <= 9; j++) {
                            dp[i][pre][prePre] = (dp[i][pre][prePre] + dp[i + 1][j][0]) % mod;
                        }
                    }
                }
            } else if (i == 1) {
                for (int pre = 0; pre < 10; pre++) {
                    for (int prePre = 0; prePre < 10; prePre++) {
                        if (pre == 1) {
                            for (int j = 2; j <= 9; j++) {
                                dp[i][pre][prePre] = (dp[i][pre][prePre] + dp[i + 1][j][pre]) % mod;
                            }
                        } else {
                            for (int j = 1; j <= 9; j++) {
                                dp[i][pre][prePre] = (dp[i][pre][prePre] + dp[i + 1][j][pre]) % mod;
                            }
                        }
                    }
                }
            } else {
                for (int pre = 0; pre < 10; pre++) {
                    for (int prePre = 0; prePre < 10; prePre++) {
                        if ((pre == 1 && prePre != 1) || prePre == 1 && pre != 1) {
                            for (int j = 2; j <= 9; j++) {
                                dp[i][pre][prePre] = (dp[i][pre][prePre] + dp[i + 1][j][pre]) % mod;
                            }
                        } else {
                            for (int j = 1; j <= 9; j++) {
                                dp[i][pre][prePre] = (dp[i][pre][prePre] + dp[i + 1][j][pre]) % mod;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(process(n, 0, 0, 0, dp2));
        System.out.println(dp[0][0][0]);
    }
}

