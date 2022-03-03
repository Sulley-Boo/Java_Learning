package com.javatest;

import java.util.LinkedList;
import java.util.List;

public class UniqueBST {
    /**
     * 给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构。
     * 题意同：给你一个整数n，求恰由n个节点组成且节点值从1到n互不相同的二叉搜索树有多少种？
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //暴力递归
    public static int process1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i <= n - 1; i++) {
            res += process1(i) * process1(n - 1 - i);
        }
        return res;
    }

    //记忆化搜索
    public static int process2(int n, int[] dp) {
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n == 0 || n == 1) {
            dp[n] = 1;
        } else {
            int res = 0;
            for (int i = 0; i <= n - 1; i++) {
                res += process2(i, dp) * process2(n - 1 - i, dp);
            }
            dp[n] = res;
        }

        return dp[n];
    }

    //动态规划法
    public static int dpWays(int n) {
        int[] dp = new int[n + 1];
        if (n < 2) {
            return 1;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    // 打印生成的每一棵树，以下是课程官方代码
    public static List<Node> generateTrees(int n) {
        return generate(1, n);
    }

    public static List<Node> generate(int start, int end) {
        List<Node> res = new LinkedList<Node>();
        if (start > end) {
            res.add(null);
        }
        Node head = null;
        for (int i = start; i < end + 1; i++) {
            head = new Node(i);
            List<Node> lSubs = generate(start, i - 1);
            List<Node> rSubs = generate(i + 1, end);
            for (Node l : lSubs) {
                for (Node r : rSubs) {
                    head.left = l;
                    head.right = r;
                    res.add(cloneTree(head));
                }
            }
        }
        return res;
    }

    public static Node cloneTree(Node head) {
        if (head == null) {
            return null;
        }
        Node res = new Node(head.value);
        res.left = cloneTree(head.left);
        res.right = cloneTree(head.right);
        return res;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int n = 4;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dp[i] = -1;
        }
        System.out.println(process1(n));
        System.out.println(process2(n, dp));
        System.out.println(dpWays(n));
        List<Node> res = generateTrees(n);
        for (Node node : res) {
            printTree(node);
        }
    }
}
