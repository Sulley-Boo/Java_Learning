package com.javatest;

import java.util.*;

public class KMP {
    /**
     * KMP算法的实现
     * 字符串str1和str2，str1是否包含str2，如果包含返回str2在str1中开始的位置。
     * 如何做到时间复杂度O(N)完成？
     */
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < m.length() || m.length() < 1) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            //刚开始都从0位置开始依次判断
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) { //判断条件即i2==0，i2已经来到0位置了，已无法搜索到子串，i1走到下一个位置
                i1++;
            } else { //i2可以往前跳，根据next数组让i2往前跳
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    public static int[] getNextArray(char[] ms) {
        //获取字符串中每个字符前面的子串最长相同前缀和后缀的数量。如[a,b,a,b]->[-1,0,0,1]
        if (ms.length == 0) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; // cn记录next[i-1]的值，在计算next[i]时需要用到
        while (i < ms.length) {
            if (ms[i - 1] == ms[cn]) { //最后一个字符和cn位置的字符比较，相同时可获得next[i] = cn + 1
                cn++;
                next[i] = cn;
                i++;
                //next[i++] = ++cn;
            } else if (cn > 0) { //如果比较的字符不相等，cn就往前跳
                cn = next[cn];
            } else { //cn无法往前跳时，将next[i]设置为0
                next[i] = 0;
                i++;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }
}
