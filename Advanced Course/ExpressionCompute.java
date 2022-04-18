package com.javatest;

import java.util.*;

public class ExpressionCompute {
    
    /**
     * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右
     * 括号，返回公式的计算结果。
     * 【举例】
     * str="48*((70-65)-43)+8*1"，返回-1816。
     * str="3+1*4"，返回7。
     * str="3+(1*4)"，返回7。
     * 【说明】
     * 1．可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
     * 2．如果是负数，就需要用括号括起来，比如"4*(-3)"。但如果负数作为公式的开头
     * 或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
     * 3．不用考虑计算过程中会发生溢出的情况。
     * Leetcode中的基本计算器系列题目。
     */

    /*----------------方法1：递归 + 单栈-------------------*/
    public static int calculate1(String s) {
        return compute(s.toCharArray(), 0)[0];
    }

    // 返回参数：[括号内的计算结果，与左括号相匹配的右括号位置]
    public static int[] compute(char[] chs, int i) {
        int num = 0;
        Deque<String> deque = new ArrayDeque<>();
        while (i < chs.length && chs[i] != ')') {
            if (chs[i] == ' ') {
                i++;
            } else if (Character.isDigit(chs[i])) { // 当前处理的是数字
                num = num * 10 + chs[i++] - '0';
            } else if (chs[i] != '(') { // 当前处理的是运算符
                addNum(deque, num);
                deque.addLast(String.valueOf(chs[i++]));
                num = 0;
            } else { // 当前处理的是左括号
                // 调用子过程，计算当前遇到的括号内的结果
                int[] res = compute(chs, i + 1);
                num = res[0];
                i = res[1] + 1; // i来到右括号下一个位置
            }
        }
        addNum(deque, num);
        return new int[]{getNum(deque), i};
    }

    public static int getNum(Deque<String> deque) { // 计算栈内的结果，栈中只有+-
        int res = Integer.parseInt(deque.pollFirst());
        while (!deque.isEmpty()) {
            String cal = deque.pollFirst();
            int num = Integer.parseInt(deque.pollFirst());
            res = cal.equals("+") ? res + num : res - num;
        }
        return res;
    }

    public static void addNum(Deque<String> deque, int num) { // 将当前的数字加入到栈中，遇到*/先处理掉
        if (!deque.isEmpty()) {
            String top = deque.pollLast();
            if (top.equals("+") || top.equals("-")) {
                deque.addLast(top);
            } else {
                int cur = Integer.parseInt(deque.pollLast());
                num = top.equals("*") ? cur * num : cur / num;
            }
        }
        deque.addLast(String.valueOf(num));
    }

    /*----------------方法2：双栈-------------------*/
    public static Map<Character, Integer> map = new HashMap<>() {{ // 定义优先级
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
    }};

    public static int calculate2(String s) {
        char[] chs = s.toCharArray();
        Deque<Integer> nums = new ArrayDeque<>(); // 数字栈
        Deque<Character> ops = new ArrayDeque<>(); // 运算符栈
        nums.addLast(0); // 防止第一个数为负数
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == ' ') {
                continue;
            } else if (Character.isDigit(chs[i])) { // 当前处理的是数字
                int j = i;
                int num = 0;
                while (j < chs.length && Character.isDigit(chs[j])) {
                    num = num * 10 + chs[j++] - '0';
                }
                nums.addLast(num);
                i = j - 1;
            } else if (chs[i] == '(') { // 遇到左括号
                ops.addLast(chs[i]);
            } else if (chs[i] == ')') { // 遇到右括号，先将与该右括号匹配的左括号内的结果计算出来
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (i > 0 && chs[i - 1] == '(') { // 防止左括号下一个数为负数
                    nums.addLast(0);
                }
                // 比较当前运算符和ops栈顶元素的优先级
                // 当前运算符优先级高时，不能先结算
                while (!ops.isEmpty() && ops.peekLast() != '(') {
                    char pre = ops.peekLast();
                    if (map.get(pre) >= map.get(chs[i])) {
                        calc(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.addLast(chs[i]);
            }
        }
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peekLast();
    }

    public static void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || ops.isEmpty() || nums.size() < 2) {
            return;
        }
        // 数字栈中取两个元素，运算符栈中取一个元素，计算结果，将结果压入数字栈
        int num2 = nums.pollLast();
        int num1 = nums.pollLast();
        char op = ops.pollLast();
        int res = switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            default -> num1 / num2;
        };
        nums.addLast(res);
    }

    public static void main(String[] args) {
        String s = "-3+(-1+(4+5 * 2)-3)+(6+8 / 4)";
        System.out.println(calculate1(s));
        System.out.println(calculate2(s));
    }
}
