package com.javatest;

import java.util.*;

public class IsPalindromeList {
    /**
     * 【题目】给定一个单链表的头节点head，请判断该链表是否为回文结构。
     * 【例子】1->2->1，返回true； 1->2->2->1，返回true；15->6->15，返回true；1->2->3，返回false。
     * 【例子】如果链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)。
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

    public static boolean IsPalindromeList_Method1(Node head){
        //方法2：将链表中的元素按顺序压入栈中，然后遍历一遍链表，将链表元素依次和栈中出栈的元素相比较
        // need n extra space
        Stack<Node> stack = new Stack<>();
        Node curr = head;
        while(curr != null){
            stack.push(curr);
            curr = curr.next;
        }
        while(head != null){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean IsPalindromeList_Method2(Node head){
        //方法3：使用快慢指针，找到链表的中间靠右一个的结点。当有2n个结点时，找第n+1个结点；当有2n+1个结点时，找第n+2个结点
        //将一半的元素入栈，然后依次比较，可以节省一般的空间
        // need n/2 extra space
        if(head == null || head.next == null){
            return true;
        }
        Stack<Node> stack= new Stack<>();
        //慢指针指向第二个结点，快指针指向第一个结点
        Node slow = head.next;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            //当快指针指向链表最后一个结点或者倒数第二个结点，循环结束
            fast = fast.next.next;
            slow = slow.next;
        }
        while(slow != null){
            stack.push(slow);
            slow = slow.next;
        }
        while(!stack.isEmpty()){
            if(head.value != stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public static boolean IsPalindromeList_Method3(Node head){
        //使用有限个变量完成判断
        // need O(1) extra space
        if(head == null || head.next == null){
            return true;
        }
        //快慢指针，当快指针指向最后一个或者倒数第二个结点时，当链表结点个数为奇数时，慢指针指向最中间的结点；当链表结点个数为偶数时，慢指针指向中间靠左的结点
        Node n1 = head;//慢指针
        Node n2 = head;//快指针
        while(n2.next != null && n2.next.next != null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = n1.next;//n2指向中间结点的下一个结点
        n1.next = null;//中间结点指向空
        Node n3 = null;
        while(n2 != null){
            //链表的后半部分反转
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;//n3指向最后一个结点
        n2 = head;//n2指向头结点
        boolean res = true;
        while(n1 != null && n2 != null){
            //头部和尾部依次往中间走，依次判断
            if(n1.value != n2.value){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        n1 = n3.next;//n1指向倒数第二个结点
        n3.next = null;
        while(n1 != null){
            //将链表的后半部分反转回来
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = null;
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        Node head1 = new Node(1);
        printLinkedList(head1);
        System.out.print(IsPalindromeList_Method1(head1) + " | ");
        System.out.print(IsPalindromeList_Method2(head1) + " | ");
        System.out.print(IsPalindromeList_Method3(head1) + " | ");
        System.out.println();
        printLinkedList(head1);
        System.out.println("=========================");

        Node head2 = new Node(1);
        head2.next = new Node(2);
        printLinkedList(head2);
        System.out.print(IsPalindromeList_Method1(head2) + " | ");
        System.out.print(IsPalindromeList_Method2(head2) + " | ");
        System.out.print(IsPalindromeList_Method3(head2) + " | ");
        System.out.println();
        printLinkedList(head2);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(IsPalindromeList_Method1(head) + " | ");
        System.out.print(IsPalindromeList_Method2(head) + " | ");
        System.out.print(IsPalindromeList_Method3(head) + " | ");
        System.out.println();
        printLinkedList(head);
        System.out.println("=========================");

    }
}
