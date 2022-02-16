package com.javatest;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {
    /**
     * 输入：
     * 正数数组costs
     * 正数数组profits
     * 正数k
     * 正数m
     * 含义：
     * costs[i]表示i号项目的花费
     * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
     * k表示你只能串行的最多做k个项目
     * m表示你初始的资金
     * 说明：
     * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
     * 输出：
     * 你最后获得的最大钱数。
     * <p>
     * 贪心策略：选择可以投资的项目中能够带来利润最多的项目。
     * 注意：优先选择花费最少，利润最多的项目的贪心策略是不正确的。
     */

    public static class Project {
        public int cost;
        public int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static class MinCostComparator implements Comparator<Project> {
        @Override
        public int compare(Project p1, Project p2) {
            return p1.cost - p2.cost;
        }
    }

    public static class MaxProfitComparator implements Comparator<Project> {
        @Override
        public int compare(Project p1, Project p2) {
            return p2.profit - p1.profit;
        }
    }

    public static int findMaximizedCapital(int[] costs, int[] profits, int k, int m) {
        Project[] projects = new Project[costs.length];
        for (int i = 0; i < costs.length; i++) {
            projects[i] = new Project(costs[i], profits[i]);
        }
        PriorityQueue<Project> minCosts = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Project> maxProfits = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < costs.length; i++) { //将所有的项目放入小根堆
            minCosts.add(projects[i]);
        }
        for (int i = 0; i < k; i++) {
            //将当前可以投资的项目从小根堆中弹出，放入大根堆
            while (!minCosts.isEmpty() && minCosts.peek().cost <= m) {
                maxProfits.add(minCosts.poll());
            }
            //如果大根堆为空，说明没有了可以投资的项目
            if (maxProfits.isEmpty()) {
                return m;
            }
            //投资大根堆中利润最大的项目
            m += maxProfits.poll().profit;
        }
        return m;
    }

    public static void main(String[] args) {
        int[] costs = {1, 2, 2, 4, 1};
        int[] profits = {4, 3, 5, 3, 2};
        int m = 1;
        int k = 4;
        System.out.println(findMaximizedCapital(costs, profits, k, m));
    }
}
