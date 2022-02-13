package com.javatest;

import java.util.PriorityQueue;

public class Test {
    /**
     * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金
     * 条，不管切成长度多大的两半，都要花费20个铜板。
     * 一群人想整分整块金条，怎么分最省铜板?
     * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为10+20+30=60。
     * 金条要分成10,20,30三个部分。 如果先把长度60的金条分成10和50，花费60；
     * 再把长度50的金条分成20和30，花费50；一共花费110铜板。
     * 但是如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，
     * 花费30；一共花费90铜板。
     * 输入一个数组，返回分割的最小代价。
     * <p>
     * 实质就是哈夫曼编码。
     * 贪心策略：优先切出最大长度的金条。
     * 逆向思维：将每个金条拼接成长金条，优先选择最小的两个金条进行拼接。
     */

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if (arr == null || arr.length < 2) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pq.size() > 1) {
            cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] golds = {10, 20, 30};
        System.out.println(lessMoney(golds));
        int[] arr = {6, 7, 8, 9};
        System.out.println(lessMoney(arr));
    }
}
