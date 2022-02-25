package com.javatest;

/**
 * 图的边集
 * 包括：
 * 1. 边的权值
 * 2. 该边由from结点出发
 * 3. 该边指向to结点
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
