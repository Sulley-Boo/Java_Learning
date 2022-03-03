package com.javatest;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKTimes {
    /**
     * 给定一个字符串类型的数组arr，求其中出现次数最多的前K个
     */
    public static class Node {
        public String str;
        public int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class minNodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    public static class maxNodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.times - o1.times;
        }
    }

    // 方法1，使用大根堆
    public static void printTopKAndRank1(String[] arr, int topK) {
        if (arr == null || arr.length == 0 || topK < 1) {
            return;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        topK = Math.min(arr.length, topK);
        PriorityQueue<Node> maxHeap = new PriorityQueue<>(new maxNodeComparator());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            maxHeap.offer(node);
        }
        for (int i = 0; i < topK; i++) {
            System.out.print(maxHeap.poll().str + " ");
        }
    }

    //方法2，使用小根堆
    public static void printTopKAndRank2(String[] arr, int topK) {
        if (arr == null || arr.length == 0 || topK < 1) {
            return;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            if (!map.containsKey(str)) {
                map.put(str, 1);
            } else {
                map.put(str, map.get(str) + 1);
            }
        }
        topK = Math.min(arr.length, topK);
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new minNodeComparator());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            if (minHeap.size() < topK) {
                minHeap.offer(node);
            } else {
                if (node.times > minHeap.peek().times) {
                    minHeap.poll();
                    minHeap.offer(node);
                }
            }
        }
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll().str + " ");
        }
    }

    // for test
    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] arr1 = {"A", "B", "A", "C", "A", "C", "B", "B", "K", "D", "D", "D", "D", "D", "A"};
        printTopKAndRank1(arr1, 2);
        System.out.println();
        printTopKAndRank2(arr1, 2);
        System.out.println();
        String[] arr2 = generateRandomArray(40,10);
        printArray(arr2);
        int topK = 3;
        printTopKAndRank1(arr2, topK);
        System.out.println();
        printTopKAndRank2(arr2,topK);
    }
}
