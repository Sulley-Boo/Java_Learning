package com.javatest;

import java.util.*;

public class TopologySort {
    /**
     * 拓扑排序算法
     * 适用范围：要求有向图，且有入度为0的节点，且没有环
     */
    public static List<Node> sortedTopology(Graph graph) {
        Queue<Node> zeroInQueue = new LinkedList<>();
        Map<Node, Integer> inMap = new HashMap<>();
        List<Node> result = new ArrayList<>();
        Node cur = null;
        for (Node node : graph.nodes.values()) {
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
            inMap.put(node, node.in);
        }
        while (!zeroInQueue.isEmpty()) {
            cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[][] matrix = {{0, 0, 2}, {0, 2, 4}, {0, 2, 5}, {0, 4, 3}, {0, 5, 3}, {0, 3, 1}};
        Graph graph = GraphGenerator.createGraph(matrix);
        List<Node> list = sortedTopology(graph);
        for (Node node : list) {
            System.out.print(node.value + " ");
        }
        System.out.println();
    }
}
