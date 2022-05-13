package com.javatest;

import java.util.*;


public class TimeComplexityAnalysis {

    /**
     * 时间限制：C/C++ 1s，其它语言2s。
     * 结论：
     * 1.当n的规模大概在10的10次方时，大约耗时3s，此时需要考虑O(logn)的解法；
     * 2.当n的规模大概在10^8至10^9之间时，耗时小于2s，O(n)的解法勉强能过；
     * 3.当n的规模大概在5 * 10^4时，O(n^2)的解法小于2ms，O(n^2)的解法勉强能过，
     * 如果大于这个规模，则需要考虑O(nlogn)的解法；
     * 4.当n的规模大概在10^7至10^8之间，耗时小于2s，如果小于这个规模，则考虑O(nlogn)的解法，
     * 否则，考虑O(n)的解法；
     * 5.当n的规模大概在2000时，耗时大于2s，如果大于等于这个规模，O(n^3)的解法过不了，
     * 对于O(n^3)的解法，n的规模最好不要超过1000。
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long k = 0;
        long n1 = sc.nextLong();
        // O(n)
        long start = System.currentTimeMillis();
        for (long i = 0L; i < n1; i++) {
            k++;
        }
        long end = System.currentTimeMillis();
        System.out.println("n的规模：" + n1);
        System.out.println("耗时：" + (end - start) + "ms");
        // n的规模：1000000000
        // 耗时：476ms

        // O(n^2)
        k = 0;
        long n2 = sc.nextLong();
        start = System.currentTimeMillis();
        for (long i = 0L; i < n2; i++) {
            for (long j = 0; j < n2; j++) {
                k++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("n的规模：" + n2);
        System.out.println("耗时：" + (end - start) + "ms");
        // n的规模：50000
        // 耗时：749ms

        // O(nlogn)
        long n3 = sc.nextLong();
        start = System.currentTimeMillis();
        for (long i = 0L; i < n3; i++) {
            for (long j = 1; j < n3; j = j * 2) {
                k++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("n的规模：" + n3);
        System.out.println("耗时：" + (end - start) + "ms");
        // n的规模：100000000
        // 耗时：861ms

        // O(n^3)
        long n4 = sc.nextLong();
        start = System.currentTimeMillis();
        for (long i = 0; i < n4; i++) {
            for (long j = 0; j < n4; j++) {
                for (long l = 0; l < n4; l++) {
                    k++;
                }
            }
        }
        end = System.currentTimeMillis();
        System.out.println("n的规模：" + n4);
        System.out.println("耗时：" + (end - start) + "ms");
        // n的规模：1000
        // 耗时：319ms
    }
}
