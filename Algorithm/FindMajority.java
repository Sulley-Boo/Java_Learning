package com.javatest;

import java.util.*;

public class FindMajority {
    /**
     * 摩尔投票问题(超级水王问题)
     * 找出数组中出现次数大于N/2的数，N表示数组的长度。
     * 扩展：找出数组中出现次数大于N/K的所有的数。
     * <p>
     * 思路：依次删除数组中的两个不相等的数，删到不能再删为止，如果没有剩下数，则一定没有水王数；如果有剩下的数，则在遍历一边数组，记录
     * 该数的真实次数，大于N/2，为水王数，否则不是水王数。
     * 流程：遍历数组，cand：当前候选数；hp：当前候选数的血量（记录）
     * 1.如果hp==0，表示没有候选数或者不要当前候选数，将当前遍历的数定为候选数，hp设为1；
     * 2.否则：
     * 1)如果当前候选数不等于arr[i]，hp--；(有两个不相等的数相互抵消)
     * 2)如果当前候选数等于arr[i]，hp++；(当前数和候选数相等，保存下来)
     */
    public static Integer findMajority(int[] arr) {
        int cand = 0;
        int hp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (hp == 0) {
                cand = arr[i];
                hp = 1;
            } else {
                if (cand == arr[i]) {
                    hp++;
                } else {
                    hp--;
                }
            }
        }
        if (hp == 0) {
            return null;
        }
        hp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (cand == arr[i]) {
                hp++;
            }
        }
        return hp > arr.length / 2 ? cand : null;
    }

    // 扩展题
    public static Set<Integer> findMajorityK(int[] arr, int K) {
        if (K < 2) {
            return null;
        }
        // 候选表，最多有K - 1条记录
        Map<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == K - 1) { // 当前数不要，每一个候选数的记录-1，删掉记录为0的候选数
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        HashMap<Integer, Integer> reals = getReals(arr, cands);
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : cands.entrySet()) {
            Integer key = entry.getKey();
            if (reals.get(key) > arr.length / K) {
                set.add(key);
            }
        }
        return set;
    }

    public static void allCandsMinusOne(Map<Integer, Integer> cands) {
        List<Integer> removeList = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : cands.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            cands.put(key, value - 1);
        }
        for (int key : removeList) {
            cands.remove(key);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, Map<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                reals.put(curNum, reals.getOrDefault(curNum, 0) + 1);
            }
        }
        return reals;
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 1, 2, 3, 4};
        System.out.println(findMajority(arr1));
        int[] arr2 = {2, 2, 3, 3, 4, 4, 5, 5};
        System.out.println(findMajority(arr2));
        int[] arr3 = {1, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5};
        System.out.println(findMajorityK(arr3, 4));
        int[] arr4 = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        System.out.println(findMajorityK(arr4, 5));
        System.out.println(findMajorityK(arr4, 6));
    }
}
