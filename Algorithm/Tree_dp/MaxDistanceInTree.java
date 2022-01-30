package com.javatest;

import java.util.*;

public class Test {
    /**
     * 二叉树节点间的最大距离问题
     * 从二叉树的节点a出发，可以向上或者向下走，但沿途的节点只能经过一次，到达节点b时路
     * 径上的节点个数叫作a到b的距离，那么二叉树任何两个节点之间都有距离，求整棵树上的最
     * 大距离。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        public int maxDistance;
        public int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int m1 = leftInfo.maxDistance;
        int m2 = rightInfo.maxDistance;
        int m3 = leftInfo.height + rightInfo.height + 1;
        int maxDistance = Math.max(m3, Math.max(m1, m2));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }
    public static int getmaxDistance(Node head) {
        return process(head).maxDistance;
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(getmaxDistance(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(getmaxDistance(head2));
    }
}
