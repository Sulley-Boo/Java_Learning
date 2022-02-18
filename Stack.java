package com.javatest;

import java.util.*;

public class Stack {
    /**
     * Stack类
     * 已实现的接口:
     * Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess
     *
     * push
     * peek
     * search
     * pop
     * isEmpty
     */
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>(); //初始化
        stack.push("1"); //将项目推到此堆栈的顶部。
        stack.push("2");
        stack.push("3");
        System.out.println(stack.peek()); //查看此堆栈顶部的对象，而不将其从堆栈中删除。
        System.out.println(stack.search("1")); //返回对象在此堆栈上的从1开始的位置。
        stack.pop(); //移除此堆栈顶部的对象，并将该对象作为此函数的值返回。
        System.out.println(stack.isEmpty()); //测试此堆栈是否为空。
    }
}
