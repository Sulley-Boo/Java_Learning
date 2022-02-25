package com.javatest;

import java.util.ArrayList;

/**
 * 图的点集
 * 包括：
 * 1. 结点的权值信息(默认权值设置为结点编号)
 * 2. 结点的出度
 * 3. 结点的入度
 * 4. 由该结点指向的点集
 * 5. 由该结点指向的边集
 */
public class Node {
    public int value;
    public int out;
    public int in;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.out = 0;
        this.in = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
