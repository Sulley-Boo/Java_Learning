package com.javatest;

import java.util.*;

public class ReverseList {
    /**
     * 反转单链表和双向链表
     * 【题目】 分别实现反转单向链表和反转双向链表的函数
     * 【要求】 如果链表长度为N，时间复杂度要求为O(N)，额外空间复杂度要求为O(1)
     *
     */

    public static class Node {
        //单链表的初始化
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class DoubleNode {
        //双向链表的初始化
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    public static void printLinkedList(Node head) {
        //打印单链表
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printDoubleLinkedList(DoubleNode head) {
        //打印双向链表
        System.out.print("Double Linked List: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

    public static Node reverseList(Node head){
        Node prev = null;
        Node curr = head;
        while (curr != null){
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static DoubleNode reverseList(DoubleNode head){
        DoubleNode prev = null;
        DoubleNode curr = head;
        DoubleNode next = null;
        while(curr != null){
            next = curr.next;
            curr.next = prev;
            curr.last = next;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node head1 = new Node(0);
        head1.next = new Node(1);
        head1.next.next = new Node(2);
        head1.next.next.next = new Node(3);
        printLinkedList(head1);
        head1 = reverseList(head1);
        printLinkedList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;
        printDoubleLinkedList(head2);
        printDoubleLinkedList(reverseList(head2));
    }
}
