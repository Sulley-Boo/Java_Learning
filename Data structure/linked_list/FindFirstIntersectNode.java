package com.javatest;

public class FindFirstIntersectNode {
    /**
     * 两个单链表相交的一系列问题
     * 【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。请实
     * 现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交，返
     * 回null
     * 【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度
     * 请达到O(1)。
     */

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            //两个链表都无环的情况
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            //两个链表都有环的情况
            return bothLoop(head1, loop1, head2, loop2);
        }
        //两个链表一个有环一个无环，链表不可能相交
        return null;
    }

    public static Node getLoopNode(Node head) {
        //获取链表的第一个入环结点，如果没有环，则返回空
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node noLoop(Node head1, Node head2) {
        //两个链表都无环的情况，返回第一个相交结点，如果不相交，则返回空
        Node n1 = head1;
        Node n2 = head2;
        int n = 0; //n最后等于两个链表长度的差值
        while (n1.next != null) {
            n++;
            n1 = n1.next;
        }
        while (n2.next != null) {
            n--;
            n2 = n2.next;
        }
        if (n1 != n2) { //两个链表最后一个结点不相等，则一定不相交
            return null;
        }
        n1 = n > 0 ? head1 : head2; //n1指向长链表的头结点
        n2 = n1 == head1 ? head2 : head1; //n2指向短链表的头结点
        n = Math.abs(n);
        while (n > 0) { //长链表先走差值步
            n--;
            n1 = n1.next;
        }
        while (n1 != n2) { //然后一起走，最终会走到第一个相交结点
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        //两个链表都有环的情况，返回第一个相交结点，如果不相交，则返回空
        if (loop1 == loop2) {
            // 两个链表入环结点是一个结点，找第一个相交结点思路同无环的情况
            Node n1 = head1;
            Node n2 = head2;
            int n = 0;
            while (n1 != loop1) {
                n++;
                n1 = n1.next;
            }
            while (n2 != loop2) {
                n--;
                n2 = n2.next;
            }
            n1 = n > 0 ? head1 : head2;
            n2 = n1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0) {
                n--;
                n1 = n1.next;
            }
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        } else {
            //两个链表的入环结点不是一个，在其中一个链表的环上找是否有另一个链表的入环结点
            //如果有，则两个链表的入环结点都是第一个相交结点，否则，两个链表不相交
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);
    }
}
