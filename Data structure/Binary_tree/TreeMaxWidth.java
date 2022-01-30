package com.javatest;

import java.util.*;

public class Test {
    /**
     * 二叉树层次遍历（宽度优先遍历）
     * 求一棵二叉树的最大宽度（哪一层结点数最多）
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void bfs(Node head) {
        Queue<Node> queue = new LinkedList<>();
        if (head == null) {
            return;
        }
        Node cur = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    public static int getMaxWidth(Node head) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> levelMap = new HashMap<>();
        if (head == null) {
            return 0;
        }
        int curLevel = 1;
        int curLevelNodes = 0;
        int maxWidth = Integer.MIN_VALUE;
        queue.add(head);
        levelMap.put(head, 1);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                maxWidth = Math.max(maxWidth, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
        }
        return Math.max(maxWidth, curLevelNodes);
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        System.out.print("层次遍历:");
        bfs(head);
        System.out.print("最大宽度:");
        System.out.println(getMaxWidth(head));
    }
}
