package com.javatest;

import java.util.LinkedList;

public class Test {

    /**
     * 判断一颗二叉树是平衡二叉树（Balance Binary Tree）？
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int getLength(Node head) {
        //递归求二叉树的高度
        if (head == null) {
            return 0;
        }
        int left = getLength(head.left);
        int right = getLength(head.right);
        return Math.max(left, right) + 1;
    }

    public static boolean isBBT_Method1(Node head) {
        //方法一：一个树是平衡二叉树，当且仅当左子树和右子树为二叉树，且左右子树高度差小于2
        if (head == null) {
            return true;
        }
        int left = getLength(head.left);
        int right = getLength(head.right);
        return isBBT_Method1(head.left) && isBBT_Method1(head.right) && (Math.abs(left - right) < 2);
    }

    public static boolean isBBT_Method2(Node head) {
        //方法二：使用树型DP的方法
        return process(head).isBalanced;
    }

    public static class ReturnType {
        //返回信息：1.树的高度 2.树是否平衡
        public int height;
        public boolean isBalanced;

        public ReturnType(int h, boolean isB) {
            height = h;
            isBalanced = isB;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, true);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                && (Math.abs(leftData.height - rightData.height) < 2);
        return new ReturnType(height, isBalanced);
    }

    public static void main(String[] args) {
        Node head1 = null;
        System.out.println(isBBT_Method1(head1));
        System.out.println(isBBT_Method2(head1));
        System.out.println("============");
        Node head2 = new Node(1);
        System.out.println(isBBT_Method1(head2));
        System.out.println(isBBT_Method2(head2));
        System.out.println("============");
        Node head3 = new Node(5);
        head3.left = new Node(3);
        head3.right = new Node(8);
        head3.left.left = new Node(2);
        head3.left.right = new Node(4);
        head3.left.left.left = new Node(1);
        head3.right.left = new Node(7);
        head3.right.left.left = new Node(6);
        head3.right.right = new Node(10);
        head3.right.right.left = new Node(9);
        head3.right.right.right = new Node(11);
        System.out.println(isBBT_Method1(head3));
        System.out.println(isBBT_Method2(head3));
        System.out.println("============");
        Node head4 = new Node(1);
        head4.left = new Node(2);
        head4.right = new Node(3);
        head4.left.left = new Node(4);
        head4.left.left.right = new Node(5);
        System.out.println(isBBT_Method1(head4));
        System.out.println(isBBT_Method2(head4));
    }
}
