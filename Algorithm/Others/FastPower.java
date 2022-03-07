package com.javatest;

public class FastPower {
    /**
     * 1. 求x的n次方，要求时间复杂度O(logN)
     * 2. 求斐波那契数列第N项，要求时间复杂度O(logN)
     * 3. 当递归式只是某个递归表达式，不存在状态转移时，即可用这种方法求解，
     * 时间复杂度O(logN)，关键在于找到状态转移矩阵。
     */
    // 求x的n次方
    public static long myPow(int x, int n) {
        long res = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) != 0) {
                res *= x;
            }
            x = x * x;
        }
        return res;
    }

    //求斐波那契数列第n项
    public static long fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        long[][] m = {{1, 1}, {1, 0}};
        long[][] res = matrixPow(m, n - 2);
        return res[0][0] + res[1][0];
    }

    //计算矩阵的幂
    public static long[][] matrixPow(long[][] m, int p) {
        long[][] res = new long[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        for (; p > 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = matrixMultiply(res, m);
            }
            m = matrixMultiply(m, m);
        }
        return res;
    }

    // 计算两个方阵相乘,请保证两个矩阵大小一样，且不为空
    public static long[][] matrixMultiply(long[][] m1, long[][] m2) {
        if (m1.length == 0 || m2.length == 0) {
            return new long[0][0];
        }
        long[][] res = new long[m1.length][m1[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                for (int k = 0; k < m1.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++) {
            System.out.println(i + ":" + fib(i));
        }
    }
}
