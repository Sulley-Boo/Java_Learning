package com.javatest;

import javax.print.DocFlavor;
import java.util.LinkedList;
import java.util.Stack;

public class IsBST {

    /**
     * 判断一颗二叉树是二叉搜索树（Binary Search Tree）？
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    public static boolean isBST_Method1(Node head) {
        //递归方法判断，根据中序遍历算法改编
        if (head == null) {
            return true;
        }
        boolean isLeftBST = isBST_Method1(head.left);
        if (!isLeftBST) {
            return false;
        }
        if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return isBST_Method1(head.right);
    }

    public static boolean isBST_Method2(Node head) {
        //非递归方法判断，根据中序遍历算法改编
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value <= preValue) {
                    return false;
                } else {
                    preValue = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

    public static boolean isBST_Method3(Node head) {
        //方法三：树型DP
        return process(head).isBST;
    }

    public static class ReturnType {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnType(boolean is, int mi, int ma) {
            isBST = is;
            min = mi;
            max = ma;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return null;
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        int min = head.value;
        int max = head.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= head.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || rightData.min <= head.value)) {
            isBST = false;
        }

        return new ReturnType(isBST, min, max);
    }

    public static boolean isBST_Method4(Node head) {
        //方法四
        if (head == null) {
            return true;
        }
        LinkedList<Node> inOrderList = new LinkedList<>();
        process1(head, inOrderList);
        int pre = Integer.MIN_VALUE;
        for (Node cur : inOrderList) {
            if (pre >= cur.value) {
                return false;
            }
            pre = cur.value;
        }
        return true;
    }

    public static void process1(Node node, LinkedList<Node> inOrderList) {
        if (node == null) {
            return;
        }
        process1(node.left, inOrderList);
        inOrderList.add(node);
        process1(node.right, inOrderList);
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right = new Node(5);
        System.out.println(isBST_Method3(head));
    }
}
