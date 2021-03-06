package com.javatest;

import java.util.*;

public class CopyListWithRandom {
    /**
     * 复制含有随机指针节点的链表
     * 【题目】一种特殊的单链表节点类描述如下
     * class Node {
     *     int value;
     *     Node next;
     *     Node rand;
     *     Node(int val) {
     *     value = val;
     *     }
     * }
     * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节
     * 点，也可能指向null。给定一个由Node节点类型组成的无环单链表的头节点
     * head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
     * 【要求】时间复杂度O(N)，额外空间复杂度O(1)
     *
     */

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static Node copyListWithRandom_Method1(Node head){
        //使用哈希表来保存链表中每个结点的克隆结点
        //根据原链表每个结点的指针设置克隆结点的每一个指针
        if(head == null){
            return null;
        }
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while(cur != null){
            map.put(cur, new Node(cur.value));//(key, value)-->(结点，克隆结点)
            cur = cur.next;
        }
        cur = head;
        while(cur != null){
            map.get(cur).next = map.get(cur.next);//设置克隆结点的next指针
            map.get(cur).rand = map.get(cur.rand);//设置克隆结点的rand指针
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRandom_Method2(Node head){
        //时间复杂度O(n)，额外空间复杂度O(1)
        if(head == null){
            return head;
        }
        Node cur = head;
        Node next = null;
        //将克隆结点放在原链表每个结点后
        while(cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        //设置克隆结点的rand指针
        while(cur != null){
            curCopy = cur.next;
            curCopy.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }
        Node res = head.next;
        cur = head;
        //将原链表和克隆结点组成的链表分离
        while(cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.next = next == null ? null : next.next;
            cur.next = next;
            cur = next;
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
        res1 = copyListWithRandom_Method1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRandom_Method2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRandom_Method1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRandom_Method2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }
}
