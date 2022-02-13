package com.javatest;

import javax.print.attribute.standard.NumberOfDocuments;
import javax.sound.midi.Soundbank;
import java.util.*;

public class PreInPosTraversal {
    /**
     * 二叉树节点结构
     * class Node<V>{
     * V value;
     * Node left;
     * Node right;
     * }
     * 用递归和非递归两种方式实现二叉树的先序、中序、后序遍历
     *
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head) {
        //先序遍历，递归
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        //中序遍历，递归
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        //后序遍历，递归
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void preOrderUnRecur(Node head) {
        //先序遍历，非递归
        System.out.print("pre-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> st = new Stack<>();
        st.push(head);
        while (!st.isEmpty()) {
            head = st.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                st.push(head.right);
            }
            if (head.left != null) {
                st.push(head.left);
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node head) {
        //中序遍历，非递归
        System.out.print("in-order:");
        if (head == null) {
            return;
        }
        Stack<Node> st = new Stack<>();
        while (!st.isEmpty() || head != null) {
            if(head != null) {
                st.push(head);
                head = head.left;
            }
            else {
                head = st.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur(Node head) {
        //后序遍历,非递归
        System.out.print("pos-order: ");
        if (head == null) {
            return;
        }
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(head);
        while (!st1.isEmpty()) {
            head = st1.pop();
            st2.push(head);
            if (head.left != null) {
                st1.push(head.left);
            }
            if (head.right != null) {
                st1.push(head.right);
            }
        }
        while (!st2.isEmpty()) {
            System.out.print(st2.pop().value + " ");
        }
        System.out.println();
    }



    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur(head);
    }
}
