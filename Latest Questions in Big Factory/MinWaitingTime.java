package com.javatest;

import java.util.PriorityQueue;

public class MinWaitingTime {
    /**
     * 谷歌原题：
     * 给定一个数组arr，长度为n
     * 表示n个服务员，每个人服务一个人的时间
     * 给定一个正数m，表示有m个人等位
     * 如果你是刚来的人，请问你需要等多久？
     * 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
     */

    // 方法1，使用小根堆
    // 小根堆按照服务员服务上一个人的结束时间从小到大排序，不需要管每个服务员的服务时间
    // 因为每个人都想要自己等待的时间最少\
    // 时间复杂度O(m)
    public static int ways1(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        for (int i = 0; i < arr.length; i++) {
            minHeap.offer(new int[]{0, arr[i]});
        }
        for (int i = 0; i < m; i++) {
            int[] cur = minHeap.poll();
            cur[0] += cur[1];
            minHeap.add(cur);
        }
        return minHeap.peek()[0];
    }

    // 方法2，使用二分查找
    // 时间复杂度O(n * logm)
    // 由于m远大于n，因此方法2时间复杂度远优于方法1
    public static int ways2(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int num : arr) {
            min = Math.min(num, min);
        }
        //最少等待时间为0，最长等待时间为min * m(所有人都找一个服务员服务)
        int left = 0;
        int right = min * m;
        int res = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int cover = 0; // cover表示在mid时间有多少人正在被服务(包括已经服务完、正在接受服务、正好准备接受服务的人)
            for (int num : arr) {
                cover += (mid / num) + 1;
            }
            if (cover >= m + 1) { // 表示在mid时间你一定能接受到服务
                right = mid - 1;
                res = mid;
            } else { // 在mid时间你接受不到服务
                left = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 5};
        System.out.println(ways1(arr1, 6));
        System.out.println(ways2(arr1, 6));
        int[] arr2 = {2, 5, 10, 4, 9, 14, 50, 34, 1, 100};
        int m2 = 100000000;
        long start = System.currentTimeMillis();
        System.out.println(ways1(arr2, m2));
        long end = System.currentTimeMillis();
        System.out.println("方法1用时:" + (end - start) + "ms");
        start = System.currentTimeMillis();
        System.out.println(ways2(arr2, m2));
        end = System.currentTimeMillis();
        System.out.println("方法2用时:" + (end - start) + "ms");

        int len = 50;
        int value = 30;
        int mMax = 3000;
        int testTime = 20000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * len) + 1;
            int[] nums = randomArray(n, value);
            int m = (int) (Math.random() * mMax);
            if (ways1(nums, m) != ways2(nums, m)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
