package com.javatest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonousStack {
    /**
     * 在数组中想找到一个数，左边和右边比这个数小(大)、且离这个数最近的位置。(数组中均为正数)
     * 如果对每一个数都想求这样的信息，能不能整体代价达到O(N)？需要使用到单调栈结构
     * 单调栈结构的原理和实现
     */

    //查找数组中每个数左边右边离它最近比它大的数的索引
    public static int[][] getNearMoreNoRepeat(int[] arr) {
        //数组中不包含重复值
        Stack<Integer> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int popIndex = stack.pop();
                int leftMoreIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftMoreIndex == -1 ? -1 : arr[leftMoreIndex];
                res[popIndex][1] = arr[i];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftMoreIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftMoreIndex == -1 ? -1 : arr[leftMoreIndex];
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static int[][] getNearMore(int[] arr) {
        //数组中包含重复值
        Stack<List<Integer>> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek().get(0)]) {
                List<Integer> popList = stack.pop();
                int leftMoreIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popIndex : popList) {
                    res[popIndex][0] = leftMoreIndex;
                    res[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            int leftMoreIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popIndex : popList) {
                res[popIndex][0] = leftMoreIndex;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }

    //查找数组中每个数左边右边离它最近比它大的数的索引
    public static int[][] getNearLessNoRepeat(int[] arr) {
        //数组中不包含重复值
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static int[][] getNearLess(int[] arr) {
        //数组中包含重复值
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                // 取位于下面位置的列表中，最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                        stack.peek().size() - 1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            // 取位于下面位置的列表中，最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(
                    stack.peek().size() - 1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 6, 1, 2, 0, 7};
        int[][] resMore1 = getNearMoreNoRepeat(arr1);
        int[][] resLess1 = getNearLessNoRepeat(arr1);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i] + ":" + resMore1[i][0] + " " + resMore1[i][1]);
        }
        System.out.println("========");
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(i + ":" + resLess1[i][0] + " " + resLess1[i][1]);
        }
        System.out.println("########");
        int[] arr2 = {6, 4, 5, 4, 1, 6, 4, 5};
        int[][] resMore2 = getNearMore(arr2);
        int[][] resLess2 = getNearLess(arr2);
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(i + ":" + resMore2[i][0] + " " + resMore2[i][1]);
        }
        System.out.println("========");
        for (int i = 0; i < arr2.length; i++) {
            System.out.println(i + ":" + resLess2[i][0] + " " + resLess2[i][1]);
        }

        System.out.println("测试进行中...");
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        for (int i = 0; i < testTimes; i++) {
            int[] testArr1 = getRandomArrayNoRepeat(size);
            int[] testArr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(testArr1), rightWay(testArr1))) {
                System.out.println("测试失败!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(testArr2), rightWay(testArr2))) {
                System.out.println("测试失败!");
                printArray(arr2);
                break;
            }
        }
    }
}
