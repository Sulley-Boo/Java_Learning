package com.javatest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    /**
     * 选择排序算法实现。
     */

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0;i < arr.length - 1;i++) {
            int minIndex = i;
            for (int j = i + 1;j< arr.length;j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-69, 20, -28, -50, 26, 3, 66, -23};
        printArray(arr);
        selectionSort(arr);
        printArray(arr);
    }
}
