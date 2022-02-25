package com.javatest;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    /**
     * 图的深度优先遍历
     * 1，利用栈实现
     * 2，从源节点开始把节点按照深度放入栈，然后弹出
     * 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
     * 4，直到栈变空
     */
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        Node cur = null;
        stack.push(node);
        set.add(node);
        System.out.print(node.value + " ");
        while (!stack.isEmpty()) {
            cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.print(next.value + " ");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{0, 1, 4}, {0, 1, 2}, {0, 2, 4}, {0, 1, 3}, {0, 2, 3},
                {0, 4, 1}, {0, 2, 1}, {0, 4, 2}, {0, 3, 1}, {0, 3, 2}};
        Graph graph = GraphGenerator.createGraph(matrix);
        Node node = graph.nodes.get(4);
        dfs(node);
    }
}
