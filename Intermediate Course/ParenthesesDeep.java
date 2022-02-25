package com.javatest;

public class ParenthesesDeep {
    /**
     * 一个合法的括号匹配序列有以下定义:
     * ①空串""是一个合法的括号匹配序列
     * ②如果"X"和"Y"都是合法的括号匹配序列,"XY"也是一个合法的括号匹配序列
     * ③如果"X"是一个合法的括号匹配序列,那么"(X)"也是一个合法的括号匹配序列
     * ④每个合法的括号序列都可以由以上规则生成。
     * 例如: "","()","()()","((()))"都是合法的括号序列
     * 对于一个合法的括号序列我们又有以下定义它的深度:
     * ①空串""的深度是0
     * ②如果字符串"X"的深度是x,字符串"Y"的深度是y,那么字符串"XY"的深度为
     * max(x,y) 3、如果"X"的深度是x,那么字符串"(X)"的深度是x+1
     * 例如: "()()()"的深度是1,"((()))"的深度是3。牛牛现在给你一个合法的括号
     * 序列,需要你计算出其深度。
     * <p>
     * 附加题：给定一个括号序列(可能合法，可能不合法)，返回其最长合法子串的长度？
     */
    public static boolean isValid(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int status = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ')' && str[i] != '(') {
                return false;
            }
            if (str[i] == ')' && --status < 0) {
                return false;
            }
            if (str[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public static int getDeep(String s) {
        char[] chs = s.toCharArray();
        if (!isValid(chs)) {
            return 0;
        }
        int res = 0;
        int count = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                res = Math.max(res, ++count);
            } else {
                count--;
            }
        }
        return res;
    }

    //动态规划法
    //暴力递归和记忆化搜索省略
    //dp[i]表示字符串中以i结尾时的最长合法子串长度
    public static int getMaxLength(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int[] dp = new int[chs.length];
        int res = 0;
        int pre = 0;
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == ')') {
                pre = i - 1 - dp[i - 1];
                if (pre >= 0 && chs[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        String test1 = "(()()((()))())";
        System.out.println(getDeep(test1));
        System.out.println(getMaxLength(test1));
        String test2 = "(()))()(()))()";
        System.out.println(getMaxLength(test2));
    }
}
