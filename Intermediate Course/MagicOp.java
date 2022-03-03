package com.javatest;

import java.util.Arrays;
import java.util.HashSet;

public class MagicOp {
    /**
     * 给一个包含n个整数元素的集合a，一个包含m个整数元素的集合b。
     * 定义magic操作为，从一个集合中取出一个元素，放到另一个集合里，且操作过
     * 后每个集合的平均值都大大于于操作前。
     * 注意以下两点：
     * 1）不可以把一个集合的元素取空，这样就没有平均值了
     * 2）值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，则a的
     * 平均值不变（因为集合元素不会重复），b的平均值可能会改变（因为x被取出
     * 了）
     * 问最多可以进行多少次magic操作？
     */

    // 请保证arr1无重复值、arr2中无重复值，且arr1和arr2肯定有数字
    public static int maxOps(int[] arr1, int[] arr2) {
        double sum1 = 0;
        for (int num : arr1) {
            sum1 += (double) num;
        }
        double sum2 = 0;
        for (int num : arr2) {
            sum2 += (double) num;
        }
        if (avg(sum1, arr1.length) == avg(sum2, arr2.length)) { //平均值相等时无法进行magic操作
            return 0;
        }
        int[] arrMore = null;
        int[] arrLess = null;
        double sumMore = 0;
        double sumLess = 0;
        arrMore = avg(sum1, arr1.length) > avg(sum2, arr2.length) ? arr1 : arr2;
        sumMore = avg(sum1, arr1.length) > avg(sum2, arr2.length) ? sum1 : sum2;
        arrLess = arrMore == arr1 ? arr2 : arr1;
        sumLess = sumMore == sum1 ? sum2 : sum1;
        Arrays.sort(arrMore);
        HashSet<Integer> setLess = new HashSet<>();
        for (int num : arrLess) {
            setLess.add(num);
        }
        int sizeMore = arrMore.length;
        int sizeLess = arrLess.length;
        int ops = 0;
        for (int i = 0; i < arrMore.length; i++) {
            double cur = (double) arrMore[i];
            if (cur < avg(sumMore, sizeMore)
                    && cur > avg(sumLess, sizeLess)
                    && !setLess.contains(arrMore[i])) {
                sumMore -= cur;
                sizeMore--;
                sumLess += cur;
                sizeLess++;
                ops++;
                setLess.add(arrMore[i]);
            }
        }
        return ops;
    }

    public static double avg(double sum, int size) {
        return sum / (double) size;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 5};
        int[] arr2 = {2, 3, 4, 5, 6};
        System.out.println(maxOps(arr1, arr2));

        int[] arr3 = {324, 54, 1233, 876, 24};
        int[] arr4 = {12, 987, 876, 345, 432, 12343, 2};
        System.out.println(maxOps(arr3, arr4));

        int[] a = {3124, 4325, 54363, 235235, 65474, 23};
        int[] b = {321, 5436, 2341, 567, 6543, 2468};
        System.out.println(maxOps(a, b));

        int[] c = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] d = {11, 12, 13, 14, 15, 16, 17, 18};
        System.out.println(maxOps(c, d));
    }
}
