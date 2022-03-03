package com.javatest;

import java.util.*;

public class SubvalueEqualK {
    /**
     * 给定一个数组arr，求差值为k的去重数字对。
     */

    public static List<List<Integer>> allPair(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i : arr) {
            set.add(i);
        }
        for (Integer cur : set) {
            if (set.contains(cur + k)) {
                ans.add(Arrays.asList(cur, cur + k));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 7, 9, 6, 6, 2, 1, 4, 13, 9};
        int k = 3;
        List<List<Integer>> list = allPair(arr, k);
        System.out.println(list);
    }
}
