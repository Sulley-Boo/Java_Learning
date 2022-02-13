package com.javatest;

public class QuickSort {
    /**
     * 随机快速排序（改进的快速排序）
     * 1）在数组范围中，等概率随机选一个数作为划分值，然后把数组通过荷兰国旗问题分
     * 成三个部分：
     * 左侧<划分值、中间==划分值、右侧>划分值
     * 2）对左侧范围和右侧范围，递归执行
     * 3）时间复杂度为O(N*logN)
     */

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        //返回一个包含两个数的数组，第一个数代表最左边划分值的位置，第二个数代表最右边划分值的位置
        int less = l - 1;
        int more = r + 1;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {5, 3, 6, 5, 1, 9, 5, 5, 6, 8, 5};
        printArray(arr1);
        quickSort(arr1);
        printArray(arr1);

        int[] arr2 = {-69, 20, -28, -50, 26, 3, 66, -23};
        printArray(arr2);
        quickSort(arr2);
        printArray(arr2);
    }
}
