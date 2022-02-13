package com.javatest;

import java.util.LinkedList;

public class IsFBT {

    /**
     * 判断一颗二叉树是满二叉树（Full Binary Tree）？
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnType {
        public int height;
        public int nodes;

        public ReturnType(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new ReturnType(height, nodes);
    }

    public static boolean isFBT(Node head) {
        return process(head).nodes == (1 << process(head).height) - 1;
    }

    public static void main(String[] args) {
        Node head1 = null;
        System.out.println(isFBT(head1));
        System.out.println("============");
        Node head2 = new Node(1);
        System.out.println(isFBT(head2));
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
        System.out.println(isFBT(head3));
        System.out.println("============");
        Node head4 = new Node(1);
        head4.left = new Node(2);
        head4.right = new Node(3);
        head4.left.left = new Node(4);
        head4.left.left.right = new Node(5);
        System.out.println(isFBT(head4));
        System.out.println("============");
        Node head5 = new Node(1);
        head5.left = new Node(2);
        head5.right = new Node(3);
        head5.left.left = new Node(4);
        head5.left.right = new Node(5);
        head5.right.left = new Node(6);
        head5.right.right = new Node(7);
        System.out.println(isFBT(head5));
    }
}
