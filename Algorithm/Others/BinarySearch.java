package com.javatest;

public class BinarySearch {
    /**
     * 二分法的详解与扩展
     * 1）在一个有序数组中，找某个数是否存在
     * 2）在一个有序数组中，找>=某个数最左侧的位置
     * 3）局部最小值问题
     */

    //查找某个数是否存在
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }

    // 找大于等于value的最左位置
    public static int nearestIndexLeft(int[] arr, int value) {
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= value) {
                r = mid - 1;
                index = mid;
            } else {
                l = mid + 1;
            }
        }
        return index;
    }

    // 在数组的指定区间，找大于等于value的最左位置
    public static int nearestIndexLeft(int[] arr, int value, int l, int r) {
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] >= value) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // 求小于等于value的最右位置
    public static int nearestIndexRight(int[] arr, int value) {
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] <= value) {
                l = mid + 1;
                index = mid;
            } else {
                r = mid - 1;
            }
        }
        return index;
    }

    // 在数组的指定区间，找小于等于value的最右位置
    public static int nearestIndexRight(int[] arr, int value, int l, int r) {
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] <= value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }
    //获取[L, R]上的最大值，最小值同理
    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    //获取局部最小值的索引
    public static int findOneLessValueIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1; // no exist
        }
        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = { 6, 5, 3, 4, 6, 7, 8 };
        printArray(arr);
        int index = findOneLessValueIndex(arr);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }
}
