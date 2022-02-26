package com.javatest;

import java.util.*;

public class Kruskal {
    public static class UnionFindSet {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> rankMap;

        public UnionFindSet(Collection<Node> nodes) {
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                rankMap.put(node, 1);
            }
        }

        private Node findHead(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != fatherMap.get(node)) {
                stack.push(node);
                node = fatherMap.get(node);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }
            return node;
        }
        
        //另一种查找结点的头部（父结点）的方法
//         private Node findFather(Node node) {
//             Node father = fatherMap.get(node);
//             if (father != node) {
//                 father = findFather(father);
//             }
//             fatherMap.put(node, father);
//             return father;
//         }

        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            Node aF = fatherMap.get(a);
            Node bF = fatherMap.get(b);
            if (aF != bF) {
                Node big = rankMap.get(aF) > rankMap.get(bF) ? aF : bF;
                Node small = big == aF ? bF : aF;
                fatherMap.put(small, big);
                rankMap.put(big, rankMap.get(small) + rankMap.get(big));
                rankMap.remove(small);
            }
        }

        public static Set<Edge> kruskalMST(Graph graph) {
            Collection<Node> nodes = graph.nodes.values();
            UnionFindSet unionFindSet = new UnionFindSet(nodes);
            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.weight - o2.weight;
                }
            });
            Set<Edge> result = new HashSet<>();
            for (Edge edge : graph.edges) {
                priorityQueue.add(edge);
            }
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                if (!unionFindSet.isSameSet(edge.from, edge.to)) {
                    result.add(edge);
                    unionFindSet.union(edge.from, edge.to);
                }
            }
            return result;
        }

        public static void main(String[] args) {
            Integer[][] matrix = {{1, 1, 2}, {1, 2, 1}, {100, 2, 4}, {100, 4, 2}, {2, 1, 3},
                    {2, 3, 1}, {1000, 1, 4}, {1000, 4, 1}, {5, 3, 4}, {5, 4, 3}};
            Graph graph = GraphGenerator.createGraph(matrix);
            Set<Edge> set = kruskalMST(graph);
            for (Edge edge : set) {
                System.out.print(edge.weight + " ");
            }
            System.out.println();
        }
    }
}
