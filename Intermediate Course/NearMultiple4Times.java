package com.javatest;

public class NearMultiple4Times {
    /**
     * 给定一个数组arr，如果通过调整可以做到arr中任意两个相邻的数字相乘是4的倍数，
     * 返回true；如果不能返回false。
     */
    public static boolean nearMultiple4Times(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        int a = 0; // 记录4的倍数的数量
        int b = 0; // 记录偶数但不是4的倍数的数量
        int c = 0; // 记录奇数的数量
        for (int num : arr) {
            if (num % 4 == 0) {
                a++;
            } else if (num % 4 != 0 && num % 2 == 0) {
                b++;
            } else {
                c++;
            }
        }
        if (b == 0) {
            return a >= c - 1;
        } else {
            return a >= c;
        }
    }

    public static void main(String[] args) {

    }
}
