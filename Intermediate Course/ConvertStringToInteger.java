package com.javatest;

public class ConvertStringToInteger {
    /**
     * 给定一个字符串，如果该字符串符合人们日常书写一个整数的形式，返回int类
     * 型的这个数；如果不符合或者越界返回-1或者报错。
     */
    // 判断给定的字符串是否有效
    public static boolean isValid(String s) {
        char[] chs = s.toCharArray();
        // 第一个字符只能是0~9或者-
        if (chs[0] != '-' && (chs[0] < '0' || chs[0] > '9')) {
            return false;
        }
        // 第一个字符是-时，后面不能没有字符，且这个字符不能是0
        if (chs[0] == '-' && (chs.length == 1 || chs[1] == '0')) {
            return false;
        }
        // 如果第一个字符是0，后面不能有字符
        if (chs[0] == '0' && chs.length > 1) {
            return false;
        }
        // 从第2个字符开始每个字符只能是0~9
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] < '0' || chs[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static int convert(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        if (!isValid(s)) {
            return -1;
        }
        char[] chs = s.toCharArray();
        boolean isNeg = chs[0] == '-';
        int cur = 0;
        int res = 0; // res存储字符串的负数表示，防止越界
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        for (int i = isNeg ? 1 : 0; i < chs.length; i++) {
            cur = '0' - chs[i];
            if (res < minq || (res == minq && cur < minr)) { // 判断越界
                return -1;
            }
            res = res * 10 + cur;
        }
        // 字符串为"2147483648"，负数形式不越界，正数形式越界
        if (!isNeg && res == Integer.MIN_VALUE) {
            return -1;
        }
        return isNeg ? res : -res;
    }

    public static void main(String[] args) {
        String test1 = "2147483647"; // max in java
        System.out.println(convert(test1));

        String test2 = "-2147483648"; // min in java
        System.out.println(convert(test2));

        String test3 = "2147483648"; // overflow
        System.out.println(convert(test3));

        String test4 = "-2147483649"; // overflow
        System.out.println(convert(test4));

        String test5 = "-123";
        System.out.println(convert(test5));
    }
}
