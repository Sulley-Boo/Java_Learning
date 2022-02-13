package com.javatest;

import java.util.*;

public class NQueens {
    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，
     * 也不在同一条斜线上。 给定一个整数n，返回n皇后的摆法有多少种。
     * n=1，返回1。 n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
     * n=8，返回92。
     */

    public static int process(int[] record, int i, int n) {
        //方法1
        if (i == n) {
            return 1;
        }
        int ans = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                ans += process(record, i + 1, n);
            }
        }
        return ans;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public static int ways2(int n) {
        //方法2，皇后数量不能超过32
        //方法1的常数级优化
		if (n < 1 || n > 32) {
			return 0;
		}
		int upperLim = n == 32 ? -1 : (1 << n) - 1;
		return process2(upperLim, 0, 0, 0);
	}

    //colLim 列的限制，1的位置不能放皇后，0的位置可以
    //leftDiaLim 左斜线的限制，1的位置不能放皇后，0的位置可以
    //rightDiaLim 右斜线的限制，1的位置不能放皇后，0的位置可以
	public static int process2(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
		if (colLim == upperLim) { //base case
			return 1;
		}
		int pos = 0;
		int mostRightOne = 0;
        //所有候选皇后的位置，都在pos上
		pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
		int res = 0;
		while (pos != 0) {
			mostRightOne = pos & (~pos + 1);
			pos = pos - mostRightOne;
			res += process2(upperLim, colLim | mostRightOne,
					(leftDiaLim | mostRightOne) << 1,
					(rightDiaLim | mostRightOne) >>> 1);
		}
		return res;
	}
    public static void main(String[] args) {
        int n = 14;
        int[] record = new int[n];
        
        long start = System.currentTimeMillis();
        System.out.println(process(record, 0, n));
		long end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
        
        
        start = System.currentTimeMillis();
		System.out.println(ways2(n));
		end = System.currentTimeMillis();
		System.out.println("cost time: " + (end - start) + "ms");
    }
}
