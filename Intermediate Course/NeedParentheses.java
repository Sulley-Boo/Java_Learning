package com.javatest;

public class NeedParentheses {
    /**
     * 一个完整的括号字符串定义规则如下:
     * ①空字符串是完整的。
     * ②如果s是完整的字符串，那么(s)也是完整的。
     * ③如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。
     * 例如，"(()())", ""和"(())()"是完整的括号字符串，"())(", "()(" 和 ")" 是不完整的括号字符串。
     * 牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化
     * 为一个完整的括号字符串。请问牛牛至少需要添加多少个括号。
     */

    public static int needParentheses(String str) {
        int count = 0; //遇到左括号++，遇到右括号--
        int res = 0; //记录最少需要添加的左括号
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count++;
            } else {
                if (count == 0) {
                    res++;
                } else {
                    count--;
                }
            }
        }
        //循环后的count如果不等于0，说明最少需要添加count个右括号
        return res + count;
    }

    public static void main(String[] args) {
        String str1 = "(()()()(())())";
        System.out.println(needParentheses(str1));
        String str2 = "((()()(()";
        System.out.println(needParentheses(str2));
        String str3 = "))((()()(()";
        System.out.println(needParentheses(str3));
    }
}
