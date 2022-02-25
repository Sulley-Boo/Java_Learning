package com.javatest;

import java.util.*;

public class Prim {
    /**
     * prim算法
     * 适用范围：要求无向图
     */
    public static Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        Set<Node> set = new HashSet<>(); //存放已经访问过的结点
        Set<Edge> result = new HashSet<>(); //存放最小生成树的边
        for (Node node : graph.nodes.values()) { //随便选择一个结点
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{1, 1, 2}, {1, 2, 1}, {100, 2, 4}, {100, 4, 2}, {2, 1, 3},
                {2, 3, 1}, {1000, 1, 4}, {1000, 4, 1}, {5, 3, 4}, {5, 4, 3}};
        Graph graph = GraphGenerator.createGraph(matrix);
        Set<Edge> set = primMST(graph);
        for (Edge edge : set) {
            System.out.print(edge.weight + " ");
        }
        System.out.println();
    }
}
