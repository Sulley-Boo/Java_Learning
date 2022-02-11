package com.javatest;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {
    /**
     * 堆排序扩展题目
     * 已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元
     * 素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的
     * 排序算法针对这个数据进行排序。
     *
     */
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();//优先级队列不加其他条件，就是小根堆
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0;i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {2,4,3,5,7,9,6,8};
        int k = 3;
        sortedArrDistanceLessK(arr, k);
        printArray(arr);
    }
}
