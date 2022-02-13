package com.javatest;

import java.util.*;

public class ReverseStackUsingRecursive {
    /**
     *给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
     * 如何实现?
     */

    public static void reverse(Stack<Integer> st) {
        // 使用递归逆序一个栈中的元素
        if (st.isEmpty()) {
            return;
        }
        int i = f(st);
        reverse(st);
        st.push(i);
    }

    public static int f(Stack<Integer> st) {
        // 使用递归移除并返回栈底元素
        int result = st.pop();
        if (st.isEmpty()) {
            return result;
        } else {
            int last = f(st);
            st.push(result);
            return last;
        }
    }
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(5);
        st.push(4);
        st.push(3);
        st.push(2);
        st.push(1);
        System.out.println(st);
        System.out.println(f(st));
        System.out.println(st);
        reverse(st);
        System.out.println(st);
    }
}
