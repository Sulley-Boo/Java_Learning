package com.javatest;

public class SubArrayMaxSum {
    /**
     * 为了保证招聘信息的质量问题，公司为每个职位设计了打分系统，打分可以为正数，也
     * 可以为负数，正数表示用户认可帖子质量，负数表示用户不认可帖子质量．打分的分数
     * 根据评价用户的等级大小不定，比如可以为 -1分，10分，30分，-10分等。假设数组A
     * 记录了一条帖子所有打分记录，现在需要找出帖子曾经得到过最高的分数是多少，用于
     * 后续根据最高分数来确认需要对发帖用户做相应的惩罚或奖励．其中，最高分的定义为：
     * 用户所有打分记录中，连续打分数据之和的最大值即认为是帖子曾经获得的最高分。例
     * 如：帖子10001010近期的打
     * 分记录为[1,1,-1,-10,11,4,-6,9,20,-10,-2],那么该条帖子曾经到达过的最高分数为
     * 11+4+(-6)+9+20=38。请实现一段代码，输入为帖子近期的打分记录，输出为当前帖子
     * 得到的最高分数。
     * 即：连续子数组的最大和问题
     */
    // 方法1，假设答案法
    // 假设最大最长的连续子数组和为i~j
    // (1) x ~ i-1 的连续子数组累计和一定小于0(x为0 ~ i-1的任意位置)
    // (2) i ~ y 的连续子数组累计和一定大于等于0(y为i ~ j中任意位置)
    public static int maxSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    // 动态规划法
    // dp[i]表示以i位置结尾的子数组的最大连续子数组和
    // 答案为dp[0] ~ dp[n - 1]中的最大值
    public static int maxSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 动态规划法，状态压缩
    public static int maxSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int pre = 0;
        int max = arr[0];
        for (int num : arr) {
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }

    // 方法4
    // 使用了分治法的思想
    // 利用了树形dp的套路
    public static int maxSum4(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1).mSum;
    }

    public static class Info {
        public int lSum; // [l,r]内以l为左端点的最大子段和
        public int rSum; // [l,r]内以r为右端点的最大子段和
        public int mSum; // [l,r]内的最大子段和
        public int iSum; // [l,r]的区间和

        public Info(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public static Info process(int[] arr, int l, int r) {
        if (l == r) { // base case
            return new Info(arr[l], arr[l], arr[l], arr[l]);
        }
        int m = l + ((r - l) >> 1);
        Info leftInfo = process(arr, l, m);
        Info rightInfo = process(arr, m + 1, r);
        int lSum = Math.max(leftInfo.lSum, leftInfo.iSum + rightInfo.lSum);
        int rSum = Math.max(rightInfo.rSum, rightInfo.iSum + leftInfo.rSum);
        int iSum = leftInfo.iSum + rightInfo.iSum;
        int mSum = Math.max(leftInfo.mSum, Math.max(rightInfo.mSum, leftInfo.rSum + rightInfo.lSum));
        return new Info(lSum, rSum, mSum, iSum);
    }

    // for test
    public static int[] generateRandomArray(int maxSize) {
        int size = (int) (Math.random() * maxSize);
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() * 500 + 1) - 250);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum1(arr1));
        System.out.println(maxSum2(arr1));
        System.out.println(maxSum3(arr1));
        System.out.println(maxSum4(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum1(arr2));
        System.out.println(maxSum2(arr2));
        System.out.println(maxSum3(arr2));
        System.out.println(maxSum4(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum1(arr3));
        System.out.println(maxSum2(arr3));
        System.out.println(maxSum3(arr3));
        System.out.println(maxSum4(arr3));
        System.out.println("========");
        int testTime = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(50);
            if (maxSum1(arr) != maxSum2(arr)
                    || maxSum2(arr) != maxSum3(arr)
                    || maxSum3(arr) != maxSum4(arr)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
