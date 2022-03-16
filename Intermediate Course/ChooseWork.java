package com.javatest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class ChooseWork {
    /**
     * 为了找到自己满意的工作，牛牛收集了每种工作的难度和报酬。牛牛选工作的标准是在难度不超过自身能力
     * 值的情况下，牛牛选择报酬最高的工作。在牛牛选定了自己的工作后，牛牛的小伙伴们来找牛牛帮忙选工作，
     * 牛牛依然使用自己的标准来帮助小伙伴们。牛牛的小伙伴太多了，于是他只好把这个任务交给了你。
     * class Job {
     * public int money;// 该工作的报酬
     * public int hard; // 该工作的难度
     * public Job(int money, int hard) {
     * this.money = money;
     * this.hard = hard;
     * }
     * }
     * 给定一个Job类型的数组jobarr，表示所有的工作。给定一个int类型的数组arr，表示所有小伙伴的能力。
     * 返回int类型的数组，表示每一个小伙伴按照牛牛的标准选工作后所能获得的报酬。
     */

    public static class Job {
        public int money;
        public int hard;

        public Job(int money, int hard) {
            this.money = money;
            this.hard = hard;
        }
    }

    // 将给定的工作按照难度从小到大，工资从大到小排列
    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            int hard = o1.hard - o2.hard;
            return hard == 0 ? o2.money - o1.money : hard;
        }
    }

    public static int[] getMoneys(Job[] jobs, int[] arr) {
        if (jobs == null || jobs.length == 0) {
            return new int[0];
        }
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>(); // 存放最终可以选择的工作
        int[] res = new int[arr.length];
        map.put(jobs[0].hard, jobs[0].money);
        Job pre = jobs[0];
        for (int i = 1; i < jobs.length; i++) {
            // 难度相同，但工资却减少(或不变)的工作不要
            // 难度增加，但工资却减少的工作不要
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money) {
                pre = jobs[i];
                map.put(pre.hard, pre.money);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < map.firstKey()) {
                res[i] = -1; // 一样工作都胜任不了
            } else {
                res[i] = map.get(map.floorKey(arr[i]));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Job[] jobs = {new Job(2000, 6), new Job(10000, 8),
                new Job(5000, 4), new Job(5000, 3),
                new Job(8000, 4), new Job(3000, 7)};
        int[] arr = {4, 10, 1, 7, 5, 3};
        int[] moneys = getMoneys(jobs, arr);
        for (int i = 0; i < moneys.length; i++) {
            System.out.print(moneys[i] + " ");
        }
    }
}
