package com.javatest;

public class Power {
    /**
     * 判断一个32位正数是不是2的幂、4的幂。
     */

    public static boolean is2Power1(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static boolean is2Power2(int n) {
        // -n == ~n + 1
        // n & (~n + 1) -->可以获取到二进制最右边的1
        return n > 0 && (n & -n) == n;
    }

    public static boolean is4Power1(int n) {
        // 0x55555555表示2进制的01010101...01010101
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) == n;
    }

    public static boolean is4Power2(int n) {
        // 0xaaaaaaaa表示2进制的10101010...10101010
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }

    public static boolean is4Power3(int n) {
        // 如果n是4的幂，那么n % 3 == 1
        // 如果n是2的幂但不是4的幂，那么n % 3 == 2;
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

    public static void main(String[] args) {
        int n1 = 32;
        System.out.println(is2Power1(n1));
        System.out.println(is2Power2(n1));
        int n2 = 4096;
        System.out.println(is4Power1(n2));
        System.out.println(is4Power2(n2));
        System.out.println(is4Power3(n2));
    }
}
