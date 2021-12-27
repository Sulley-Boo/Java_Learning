package com.javatest;

import java.util.*;

public class Test {
    /**
     * 将单向链表按某值划分成左边小、中间相等、右边大的形式
     * 【题目】给定一个单链表的头节点head，节点的值类型是整型，再给定一个整
     * 数pivot。实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的
     * 节点，中间部分都是值等于pivot的节点，右部分都是值大于pivot的节点。
     * 【进阶】在实现原问题功能的基础上增加如下的要求
     * 【要求】调整后所有小于pivot的节点之间的相对顺序和调整前一样
     * 【要求】调整后所有等于pivot的节点之间的相对顺序和调整前一样
     * 【要求】调整后所有大于pivot的节点之间的相对顺序和调整前一样
     * 【要求】时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
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

    public static Node listPartition_Method1(Node head, int pivot){
        //将链表中的每一个结点保存到数组中来处理
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while(cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        while(i < nodeArr.length){
            nodeArr[i] = cur;
            cur = cur.next;
            i++;
        }
        arrPartition(nodeArr, pivot);
        for(i = 1;i < nodeArr.length;i++){
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot){
        //在数组中进行划分操作
        int left = 0;
        int right = nodeArr.length - 1;
        int index = 0;
        while(index <= right){
            if(nodeArr[index].value < pivot){
                swap(nodeArr, index, left);
                index++;
                left++;
            }
            else if(nodeArr[index].value == pivot){
                index++;
            }
            else {
                swap(nodeArr, index, right);
                right--;
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b){
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }

    public static Node listPartition_Method2(Node head, int pivot){
        //仅使用有限个变量完成操作
        //时间复杂度O(n),额外空间复杂度O(1)
        //相较于第一种方法，每个结点的相对位置不会发生改变
        if(head == null){
            return head;
        }
        Node sH = null;//小于区域的头
        Node sT = null;//小于区域的尾
        Node eH = null;//等于区域的头
        Node eT = null;//等于区域的尾
        Node bH = null;//大于区域的头
        Node bT = null;//大于区域的尾
        Node cur = head;
        Node next = null;
        while(cur != null){
            //将链表中的结点划分到三个区域中
            next = cur.next;//保存下一个结点
            cur.next = null;//将原链表指针断开
            if(cur.value < pivot){
                if(sH == null){
                    sH = cur;
                    sT = cur;
                }
                else{
                    sT.next = cur;
                    sT = sT.next;
                }
            }
            else if(cur.value == pivot){
                if(eH == null){
                    eH = cur;
                    eT = cur;
                }
                else{
                    eT.next = cur;
                    eT = eT.next;
                }
            }
            else{
                if(bH == null){
                    bH = cur;
                    bT = cur;
                }
                else{
                    bT.next = cur;
                    bT = bT.next;
                }
            }
            cur = next;
        }
        //将三个区域重新连接起来
        if(sT != null){
            if(eT == null){
                sT.next = bH;
            }
            else{
                sT.next = eH;
            }
        }
        if(eT != null){
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition_Method1(head1, 5);
        printLinkedList(head1);

        Node head2 = new Node(7);
        head2.next = new Node(9);
        head2.next.next = new Node(1);
        head2.next.next.next = new Node(8);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = new Node(2);
        head2.next.next.next.next.next.next = new Node(5);
        printLinkedList(head2);
        head2 = listPartition_Method2(head2, 5);
        printLinkedList(head2);
    }
}
