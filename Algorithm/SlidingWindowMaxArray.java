package com.javatest;

import java.util.*;

public class Test {
    /**
     * 由一个代表题目，引出一种结构
     * 【题目】
     * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次 向右边滑
     * 一个位置。
     * 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时:
     * [4 3 5]4 3 3 6 7
     * 4[3 5 4]3 3 6 7
     * 4 3[5 4 3]3 6 7
     * 4 3 5[4 3 3]6 7
     * 4 3 5 4[3 3 6]7
     * 4 3 5 4 3[3 6 7]
     * 窗口中最大值为5 窗口中最大值为5 窗口中最大值为5 窗口中最大值为4 窗口中最大值为6
     * 窗口中最大值为7
     * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
     * 请实现一个函数。 输入:整型数组arr，窗口大小为w。
     * 输出:一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的 以本题为例，结果应该
     * 返回{5,5,5,4,6,7}
     * <p>
     * 窗口只能右边界或左边界向右滑的情况下，维持窗口内部最大值或者最小值快速更新的结构。
     */

    public static int[] getMaxWindow_Method1(int[] arr, int w) {
        //方法一：滑动窗口获得每个窗口的最大值
        int[] res = new int[arr.length + 1 - w];
        int index = 0;
        LinkedList<Integer> deque = new LinkedList<>(); //使用双端队列来存储信息(数组下标)
        for (int i = 0; i < arr.length; i++) {
            //队列里的元素按照降序排列，不符合规则的元素直接移除
            while (!deque.isEmpty() && arr[deque.peekLast()] < arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (deque.peekFirst() <= i - w) { //队首元素已不在当前窗口中，移除
                deque.pollFirst();
            }
            if (i >= w - 1) { //当遍历到第w - 1个元素开始产生窗口最大值
                res[index++] = arr[deque.peekFirst()];
            }
        }
        return res;
    }

    public static int[] getMaxWindow_Method2(int[] arr, int w) {
        //方法二：使用优先权队列
        int[] res = new int[arr.length + 1 - w];
        int index = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < arr.length; i++) {
            pq.offer(new int[]{arr[i], i});
            while (pq.peek()[1] <= i - w) {
                pq.poll();
            }
            if (i >= w - 1) {
                res[index++] = pq.peek()[0];
            }
        }
        return res;
    }

    public static int[] getMaxWindow_Method3(int[] nums, int k) {
        //方法三：分块+预处理
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    public static void printArray(int[] arr) {
        //打印数组
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {9, 10, 9, -7, -4, -8, 2, -6};
//        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        int w = 5;
        int[] res1 = getMaxWindow_Method1(arr, w);
        int[] res2 = getMaxWindow_Method2(arr, w);
        int[] res3 = getMaxWindow_Method3(arr, w);
        printArray(res1);
        printArray(res2);
        printArray(res3);
    }
}
