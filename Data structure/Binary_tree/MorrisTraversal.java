package com.javatest;

import java.util.*;

public class MorrisTraversal {
    /**
     * 二叉树的Morris遍历
     * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
     *
     * 假设来到当前节点cur，开始时cur来到头节点位置
     *     1）如果cur没有左孩子，cur向右移动(cur = cur.right)
     *     2）如果cur有左孩子，找到左子树上最右的节点mostRight：
     *         a.如果mostRight的右指针指向空，让其指向cur，然后cur向左移动(cur = cur.left)
     *         b.如果mostRight的右指针指向cur，让其指向null，然后cur向右移动(cur = cur.right)
     *     3）cur为空时遍历停止
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

    public static void morris(Node head) {
        //Morris遍历，时间复杂度O(n)，额外空间复杂度O(1)
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head) {
        //Morris先序遍历
        //只会访问一次的结点：直接打印
        //可以访问两次的结点：第一次访问时打印
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.value + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(Node head) {
        //Morris中序遍历
        //只会访问一次的结点：直接打印
        //可以访问两次的结点：第二次访问时打印
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
    }

    public static void morrisPos(Node head) {
        //Morris后序遍历
        //只会访问一次的结点：无视
        //可以访问两次的结点：第二次访问时进行以下操作：
        //打印该结点左孩子的右边界；
        //循环结束后，打印根结点的右边界
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
    }

    public static void printEdge(Node head) {
        //从该结点开始，逆序打印右孩子
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node head) {
        //逆转右边界
        Node pre = null;
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        System.out.print("Pre:");
        morrisPre(head);
        System.out.println();
        System.out.print("In:");
        morrisIn(head);
        System.out.println();
        System.out.print("Pos:");
        morrisPos(head);
    }
}
