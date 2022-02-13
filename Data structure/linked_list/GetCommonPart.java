package com.javatest;

import java.util.*;

public class GetCommonPart {
    /**
     * 打印两个有序链表的公共部分
     * 【题目】 给定两个有序链表的头指针head1和head2，打印两个链表的公共部分。
     * 【要求】 如果两个链表的长度之和为N，时间复杂度要求为O(N)，额外空间复杂度要求为O(1).
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



    public static void printLinkedList(Node head) {
        //打印单链表
        System.out.print("Linked List: ");
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void getCommonPart(Node head1, Node head2, Node head){
        //获取两个单链表的公共部分
        while (head1 != null && head2 != null){
            if(head1.value < head2.value){
                head1 = head1.next;
            }
            else if(head1.value > head2.value){
                head2 = head2.next;
            }
            else{
                head.next = new Node(head1.value);
                head = head.next;
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(2);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(6);
        printLinkedList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(5);
        head2.next.next.next = new Node(7);
        head2.next.next.next.next = new Node(8);
        printLinkedList(head2);

        Node head = new Node(0);
        getCommonPart(head1, head2, head);
        printLinkedList(head.next);
    }
}
