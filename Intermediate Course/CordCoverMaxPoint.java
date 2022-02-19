package com.javatest;

import java.util.Arrays;

public class CordCoverMaxPoint {
    /**
     * 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n－1]，
     * 给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
     * <p>
     * 方法1：维持绳子右边界，找>=绳子左边界的最左侧位置，从而获得绳子能覆盖的点数。
     * 方法2：维持一个滑动窗口，窗口大小不能大于L，初始时l和r都等于0，r先向右移动，r不能移动时l再向右移动。
     */
    //方法1
    public static int maxPoint1(int[] arr, int L) {
        //时间复杂度O(NlogN)
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            // i是绳子的右边界索引，绳子的左边界是arr[i] - L，找到数组arr中>=左边界的最左边的位置索引
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    //获取arr[0...R]上，满足>=value的最左侧的位置索引
    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    //方法2
    public static int maxPoint2(int[] arr, int L) {
        //l和r的范围都是0到N-1
        //时间复杂度O(N)
        int l = 0;
        int r = 0;
        int res = 1;
        for (; l < arr.length; l++) {
            while (r < arr.length && arr[r] - arr[l] <= L) {
                r++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 500000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int L = (int) (Math.random() * 11);
            Arrays.sort(arr);
            if (maxPoint1(arr, L) != maxPoint2(arr, L)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
