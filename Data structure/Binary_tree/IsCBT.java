package com.javatest;

import java.util.LinkedList;

public class Test {

    /**
     *
     * 判断一颗二叉树是完全二叉树（Complete Binary Tree）？
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

    public static boolean isCBT(Node head) {
        LinkedList<Node> queue = new LinkedList<>();
        Node l = null;
        Node r = null;
        boolean leaf = false;//用于判断结点是否到达叶子结点
        if (head == null) {
            return true;
        }
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;

            if ((l == null && r != null) ||(leaf && (l != null || r != null))) {
                //第一个判断条件：结点有右孩子没有左孩子
                //第二个判断条件：到达叶子结点，发现当前叶子结点竟然有孩子
                return false;
            }

            if (l != null) {
                queue.add(l);
            }
            if(r != null) {
                queue.add(r);
            }
            else {
                //当前结点右孩子为空，如果是完全二叉树的话，下一个结点一定是叶子结点
                leaf = true;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Node head1 = null;
        System.out.println(isCBT(head1));
        System.out.println("============");
        Node head2 = new Node(1);
        System.out.println(isCBT(head2));
        System.out.println("============");
        Node head3 = new Node(2);
        head3.left = new Node(1);
        head3.right = new Node(3);
        head3.left.left = new Node(5);
        head3.left.right = new Node(6);
        head3.right.left = new Node(8);
        System.out.println(isCBT(head3));
        System.out.println("============");
        Node head4 = new Node(4);
        head4.left = new Node(2);
        head4.right = new Node(3);
        head4.left.left = new Node(5);
        head4.left.right = new Node(6);
        head4.right.right = new Node(8);
        System.out.println(isCBT(head4));
    }
}
