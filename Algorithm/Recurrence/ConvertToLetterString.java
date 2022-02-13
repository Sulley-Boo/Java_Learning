package com.javatest;

import java.util.*;

public class ConvertToLetterString {
    /**
     * 规定1和A对应、2和B对应、3和C对应...
     * 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
     */

    public static int process(char[] chs, int i) {
        if (i == chs.length) {
            return 1;
        }
        if (chs[i] == '0') {
            return 0;
        }
        if (chs[i] == '1') {
            int res = process(chs, i + 1);
            if (i + 1 < chs.length) {
                res += process(chs, i + 2);
            }
            return res;
        }
        if (chs[i] == '2') {
            int res = process(chs, i + 1);
            if (i + 1 < chs.length && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
                res += process(chs, i + 2);
            }
            return res;
        }
        return process(chs, i + 2);
    }

    public static void main(String[] args) {
        String numbers = "11111";
        char[] chs = numbers.toCharArray();
        System.out.println(process(chs, 0));
    }
}
