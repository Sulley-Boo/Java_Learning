package com.javatest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BFS {
    /**
     * 图的宽度优先遍历
     * 1，利用队列实现
     * 2，从源节点开始依次按照宽度进队列，然后弹出
     * 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
     * 4，直到队列变空
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        Node cur = null;
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            cur = queue.poll();
            System.out.print(cur.value + " ");
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{0, 1, 4}, {0, 1, 2}, {0, 2, 4}, {0, 1, 3}, {0, 2, 3},
                {0, 4, 1}, {0, 2, 1}, {0, 4, 2}, {0, 3, 1}, {0, 3, 2}};
        Graph graph = GraphGenerator.createGraph(matrix);
        Node node = graph.nodes.get(4);
        bfs(node);
    }
}
