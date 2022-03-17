package com.javatest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Coffee {
    /**
     * 京东原题：
     * 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
     * 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
     * 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
     * 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
     * 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
     * 四个参数：arr, n, a, b
     * 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。
     */
    public static class Mechine {
        public int workTime;
        public int timePoint;

        public Mechine(int workTime, int timePoint) {
            this.workTime = workTime;
            this.timePoint = timePoint;
        }
    }

    public static class MechineComparator implements Comparator<Mechine> {
        @Override
        public int compare(Mechine o1, Mechine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    // 输出数组，数组元素对应每一个人喝完咖啡的时间
    // 每一个人优先考虑的是咖啡制造时间和等待时间都尽可能的少
    public static int[] makeCoffee(int[] arr, int n) {
        PriorityQueue<Mechine> heap = new PriorityQueue<>(new MechineComparator());
        int[] res = new int[n];
        for (int i = 0; i < arr.length; i++) {
            heap.offer(new Mechine(arr[i], 0));
        }
        for (int i = 0; i < n; i++) {
            Mechine cur = heap.poll();
            cur.timePoint += cur.workTime;
            res[i] = cur.timePoint;
            heap.offer(cur);
        }
        return res;
    }

    // 暴力递归求解
    public static int minTime1(int[] arr, int n, int a, int b) {
        int[] drinks = makeCoffee(arr, n);
        return washCupBestTime1(drinks, a, b, 0, 0);
    }

    // 0...index-1的杯子已经变干净，当前处理index号杯子
    // 处理index到n - 1的杯子，返回全部洗干净的最早时间点
    // free代表洗杯子的机器能变空闲的时间
    public static int washCupBestTime1(int[] drinks, int a, int b, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // 第index号杯子选择洗
        // 杯子在机器变空闲后才能洗，如果在drinks[index]时刻机器不空闲，需要先等待
        int selfClean1 = Math.max(drinks[index], free) + a;
        // 剩下的杯子处理完的最早时间点
        int restClean1 = washCupBestTime1(drinks, a, b, index + 1, selfClean1);
        // 由于剩下的杯子可能比index号杯子先处理完，返回大的那个
        int p1 = Math.max(selfClean1, restClean1);

        // 第index号杯子选择挥发
        int selfClean2 = drinks[index] + b; // index号杯子变干净时间点
        // 剩下的杯子全部处理完的最早时间点
        int restClean2 = washCupBestTime1(drinks, a, b, index + 1, free);
        // 同样返回大的那个
        int p2 = Math.max(selfClean2, restClean2);
        // 最终返回花费时间最少的那个
        return Math.min(p1, p2);
    }

    // 暴力递归改动态规划
    public static int minTime2(int[] arr, int n, int a, int b) {
        int[] drinks = makeCoffee(arr, n);
        return washCupBestTime2(drinks, a, b);
    }

    public static int washCupBestTime2(int[] drinks, int a, int b) {
        int maxFree = 0;
        for (int num : drinks) {
            maxFree = Math.max(maxFree, num) + a;
        }
        int[][] dp = new int[drinks.length + 1][maxFree + 1];
        for (int index = drinks.length - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                int selfClean1 = Math.max(drinks[index], free) + a;
                if (selfClean1 > maxFree) {
                    break;
                }
                int restClean1 = dp[index + 1][selfClean1];
                int p1 = Math.max(selfClean1, restClean1);
                int selfClean2 = drinks[index] + b;
                int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfClean2, restClean2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 7};
        System.out.println(minTime1(arr, 10, 3, 7));
        System.out.println(minTime2(arr, 10, 3, 7));
        int len = 10;
        int max = 10;
        int testTime = 1000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = randomArray(len, max);
            int n = (int) (Math.random() * 20) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            if (minTime1(arr1, n, a, b) != minTime2(arr1, n, a, b)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
