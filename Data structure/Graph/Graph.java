package com.javatest;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图的集合
 * 包括：
 * 1. 点集
 * 2. 边集
 */
public class Graph {
    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
