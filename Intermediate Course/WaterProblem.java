package com.javatest;

import java.util.Stack;

public class WaterProblem {
    /**
     * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，
     * 请返回容器能装多少水
     * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容
     * 器可以装下5格水
     * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
     * 即：Leetcode中的接雨水问题
     */

    // 方法1：常规方法，对于i位置，使用遍历的方法找到0~i-1的最大值和i+1~n-1位置上的最大值
    // 时间复杂度O(n^2),额外空间复杂度O(1)
    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(arr[l], leftMax);
            }
            for (int r = i + 1; r < arr.length; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }
            res += Math.max(0, Math.min(leftMax, rightMax) - arr[i]);
        }
        return res;
    }

    // 方法2：使用一个预处理数组，对于i位置，i+1~n-1位置上的最大值通过查表获取，0~i-1上的最大值在遍历中更新
    // 时间复杂度O(n)，额外空间复杂度O(n)
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int[] rightMax = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i + 1]);
        }
        int leftMax = 0;
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            res += Math.max(0, Math.min(leftMax, rightMax[i]) - arr[i]);
            leftMax = Math.max(leftMax, arr[i]);
        }
        return res;
    }

    // 方法3：使用两个预处理数组，对于i位置，leftMax记录0~i-1的最大值，rightMax记录i+1~n-1位置上的最大值
    // 时间复杂度O(n)，额外空间复杂度O(n)
    public static int getWater3(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int[] leftMax = new int[arr.length];
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i - 1]);
        }
        int[] rightMax = new int[arr.length];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i + 1]);
        }
        int res = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            res += Math.max(0, Math.min(leftMax[i], rightMax[i]) - arr[i]);
        }
        return res;
    }

    // 方法4：不使用额外的数组，只使用有限个变量，双指针
    // 时间复杂度O(n)，额外空间复杂度O(1)
    public static int getWater4(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int l = 1, r = arr.length - 2;
        int res = 0;
        while (l <= r) {
            if (leftMax < rightMax) {
                res += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l]);
                l++;
            } else {
                res += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r]);
                r--;
            }
        }
        return res;
    }

    // 方法5：使用单调栈结构
    // 时间复杂度O(n)，额外空间复杂度O(n)
    public static int getWater5(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int topIndex = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int curWidth = i - stack.peek() - 1;
                int curHeight = Math.min(arr[i], arr[stack.peek()]) - arr[topIndex];
                res += curHeight * curWidth;
            }
            stack.push(i);
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray() {
        int[] arr = new int[(int) (Math.random() * 98) + 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 200) + 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2, 5, 2, 4};
        System.out.println(getWater1(arr1) + "," +
                getWater2(arr1) + "," +
                getWater3(arr1) + "," +
                getWater4(arr1) + "," +
                getWater5(arr1));
        int[] arr2 = {4, 5, 1, 3, 2};
        System.out.println(getWater1(arr2) + "," +
                getWater2(arr2) + "," +
                getWater3(arr2) + "," +
                getWater4(arr2) + "," +
                getWater5(arr2));

        int testTime = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            if (getWater1(arr) != getWater2(arr) ||
                    getWater2(arr) != getWater3(arr) ||
                    getWater3(arr) != getWater4(arr) ||
                    getWater4(arr) != getWater5(arr)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
