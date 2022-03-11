package com.javatest;

public class BiggestSubBSTInTree {
    /**
     * 找到一棵二叉树中，最大的搜索二叉子树，返回最大搜索二叉子树的节点个数
     * 和头结点(若有多个最大搜索子树，返回任意一个即可)。
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //方法一
    public static class Info1 {
        public Node maxBSTHead;
        public int maxBSTSize;
        public boolean isBST;
        public int max;
        public int min;

        public Info1(Node maxBSTHead, int maxBSTSize, boolean isBST, int max, int min) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info1 process1(Node x) {
        if (x == null) {
            return null;
        }
        Info1 leftInfo = process1(x.left);
        Info1 rightInfo = process1(x.right);
        int min = x.value;
        int max = x.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        Node maxBSTHead = null;
        int maxBSTSize = 0;
        if (leftInfo != null) {
            maxBSTHead = leftInfo.maxBSTHead;
            maxBSTSize = leftInfo.maxBSTSize;
        }
        if (rightInfo != null && rightInfo.maxBSTSize > maxBSTSize) {
            maxBSTHead = rightInfo.maxBSTHead;
            maxBSTSize = rightInfo.maxBSTSize;
        }
        boolean isBST = false;
        if ((leftInfo == null || leftInfo.isBST) && (rightInfo == null || rightInfo.isBST)) {
            if ((leftInfo == null || leftInfo.max < x.value) && (rightInfo == null || rightInfo.min > x.value)) {
                isBST = true;
                maxBSTHead = x;
                int leftSize = leftInfo == null ? 0 : leftInfo.maxBSTSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.maxBSTSize;
                maxBSTSize = leftSize + rightSize + 1;
            }
        }
        return new Info1(maxBSTHead, maxBSTSize, isBST, max, min);
    }

    //方法二
    public static class Info2 {
        public Node maxBSTHead;
        public int maxBSTSize;
        public int max;
        public int min;

        public Info2(Node maxBSTHead, int maxBSTSize, int max, int min) {
            this.maxBSTHead = maxBSTHead;
            this.maxBSTSize = maxBSTSize;
            this.max = max;
            this.min = min;
        }
    }

    public static Info2 process2(Node x) {
        if (x == null) {
            return new Info2(null, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Info2 leftInfo = process2(x.left);
        Info2 rightInfo = process2(x.right);
        int min = Math.min(x.value, Math.min(leftInfo.min, rightInfo.min));
        int max = Math.max(x.value, Math.max(leftInfo.max, rightInfo.max));

        int maxBSTSize = Math.max(leftInfo.maxBSTSize, rightInfo.maxBSTSize);
        Node maxBSTHead = leftInfo.maxBSTSize >= rightInfo.maxBSTSize ? leftInfo.maxBSTHead : rightInfo.maxBSTHead;
        if (leftInfo.maxBSTHead == x.left &&
                rightInfo.maxBSTHead == x.right &&
                leftInfo.max < x.value && rightInfo.min > x.value) {
            maxBSTHead = x;
            maxBSTSize = leftInfo.maxBSTSize + rightInfo.maxBSTSize + 1;
        }
        return new Info2(maxBSTHead, maxBSTSize, max, min);
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.left = new Node(1);
        head.left.left = new Node(0);
        head.left.right = new Node(3);
        head.right = new Node(12);
        head.right.left = new Node(10);
        head.right.left.left = new Node(4);
        head.right.left.left.left = new Node(2);
        head.right.left.left.right = new Node(5);
        head.right.left.right = new Node(14);
        head.right.left.right.left = new Node(11);
        head.right.left.right.right = new Node(15);
        head.right.right = new Node(13);
        head.right.right.left = new Node(20);
        head.right.right.right = new Node(16);
        printTree(head);
        System.out.println("MaxBSTSize:" + process1(head).maxBSTSize);
        printTree(process1(head).maxBSTHead);
        System.out.println("========");
        printTree(head);
        System.out.println("MaxBSTSize:" + process2(head).maxBSTSize);
        printTree(process2(head).maxBSTHead);

        Node head1 = new Node(5);
        head1.left = new Node(2);
        head1.right = new Node(9);
        head1.left.left = new Node(1);
        head1.left.right = new Node(3);
        head1.left.right.right = new Node(4);
        head1.right.left = new Node(7);
        head1.right.right = new Node(10);
        head1.left.left = new Node(1);
        head1.right.left.left = new Node(6);
        head1.right.left.right = new Node(8);
        System.out.println("========");
        printTree(head1);
        System.out.println("MaxBSTSize:" + process1(head1).maxBSTSize);
        printTree(process1(head1).maxBSTHead);
        System.out.println("========");
        printTree(head1);
        System.out.println("MaxBSTSize:" + process2(head1).maxBSTSize);
        printTree(process2(head1).maxBSTHead);
    }
}
