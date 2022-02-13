package com.javatest;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.Stack;

public class LowestCommonAncestor {

    /**
     * 给定两个二叉树的节点node1和node2，找到他们的最低公共祖先节点。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestCommonAncestor(Node head, Node n1, Node n2) {
        if (head == null || head == n1 || head == n2) {
            return head;
        }
        Node left = lowestCommonAncestor(head.left, n1, n2);
        Node right = lowestCommonAncestor(head.right, n1, n2);
        if (left != null && right != null) {
            return head;
        }
        return left == null ? right : left;
    }


    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right = new Node(5);
        Node n1 = head.left.left;
        Node n2 = head.left.right;
        System.out.println(lowestCommonAncestor(head, n1, n2).value);
    }
}
