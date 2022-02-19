package com.javatest;

import java.util.HashMap;
import java.util.Stack;

public class MaxSumInTree {
    /**
     * 二叉树每个结点都有一个int型权值，给定一棵二叉树，要求计算出从根结点到
     * 叶结点的所有路径中，权值和最大的值为多少。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    //递归方法求解
    public static int maxSum = Integer.MIN_VALUE;

    public static int getMaxSum(Node head) {
        if (head == null) {
            return 0;
        }
        dfs(head, 0);
        return maxSum;
    }

    //pre表示来到当前节点时，根节点到当前节点上一个节点的路径和
    public static void dfs(Node head, int pre) {
        if (head.left == null && head.right == null) {
            maxSum = Math.max(maxSum, head.value + pre);
            return;
        }
        if (head.left != null) {
            dfs(head.left, pre + head.value);
        }
        if (head.right != null) {
            dfs(head.right, pre + head.value);
        }
    }

    //非递归方法求解
    public static int getMaxSumUnrecursive(Node head) {
        int max = 0;
        HashMap<Node, Integer> sumMap = new HashMap<>();
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            sumMap.put(head, head.value);
            while (!stack.isEmpty()) {
                head = stack.pop();
                if (head.left == null && head.right == null) {
                    max = Math.max(max, sumMap.get(head));
                }
                if (head.right != null) {
                    sumMap.put(head.right, sumMap.get(head) + head.right.value);
                    stack.push(head.right);
                }
                if (head.left != null) {
                    sumMap.put(head.left, sumMap.get(head) + head.left.value);
                    stack.push(head.left);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(1);
        head.left.right = new Node(5);
        head.right = new Node(-7);
        head.right.left = new Node(3);
        System.out.println(getMaxSum(head));
        System.out.println(getMaxSumUnrecursive(head));
    }
}
