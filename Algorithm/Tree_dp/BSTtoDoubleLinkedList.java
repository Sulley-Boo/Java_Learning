package com.javatest;

import java.util.LinkedList;
import java.util.Queue;

public class BSTtoDoubleLinkedList {
    /**
     * 双向链表节点结构和二叉树节点结构是一样的，如果你把last认为是left，
     * next认为是right的话。
     * 给定一个搜索二叉树的头节点head，请转化成一条有序的双向链表，并返回链
     * 表的头节点。
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", right=" + right +
                    '}';
        }
    }

    public static Node convert1(Node head) {
        Queue<Node> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        head = queue.poll();
        Node pre = head;
        pre.left = null;
        Node cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            cur.left = pre;
            pre.right = cur;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    public static void inOrderToQueue(Node head, Queue<Node> queue) {
        if (head == null) {
            return;
        }
        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    public static class Info {
        public Node start;
        public Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node convert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        x.left = leftInfo.end;
        x.right = rightInfo.start;
        if (leftInfo.end != null) {
            leftInfo.end.right = x;
        }
        if (rightInfo.start != null) {
            rightInfo.start.left = x;
        }
        Node start = leftInfo.start == null ? x : leftInfo.start;
        Node end = rightInfo.end == null ? x : rightInfo.end;
        return new Info(start, end);
    }

    public static void printBSTInOrder(Node head) {
        System.out.print("BST in-order: ");
        if (head != null) {
            inOrderPrint(head);
        }
        System.out.println();
    }

    public static void inOrderPrint(Node head) {
        if (head == null) {
            return;
        }
        inOrderPrint(head.left);
        System.out.print(head.value + " ");
        inOrderPrint(head.right);
    }

    public static void printDoubleLinkedList(Node head) {
        System.out.print("Double Linked List: ");
        Node end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.right;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.left;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);
        printBSTInOrder(head);
        head = convert1(head);
        System.out.println(head);
        printDoubleLinkedList(head);
        System.out.println("========");
        head = new Node(5);
        head.left = new Node(2);
        head.right = new Node(9);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.left.right.right = new Node(4);
        head.right.left = new Node(7);
        head.right.right = new Node(10);
        head.left.left = new Node(1);
        head.right.left.left = new Node(6);
        head.right.left.right = new Node(8);
        printBSTInOrder(head);
        head = convert2(head);
        System.out.println(head);
        printDoubleLinkedList(head);
    }
}
