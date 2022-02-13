package com.javatest;

import java.util.*;

public class PrintAllPermutations {
    /**
     * 打印一个字符串的全部排列
     * 打印一个字符串的全部排列，要求不要出现重复的排列
     *
     */

    public static List<String> ans;
    public static void process(char[] chs, int k) {
        if (k == chs.length) { //base case
            ans.add(String.valueOf(chs));
        }
        boolean[] visit = new boolean[26];
        for (int i = k;i < chs.length;i++) {
            // 对第k个字符做决策，第k个字符可以和后面所有的字符交换
            if (!visit[chs[i] - 'a']) { //对当前的字符做判断，如果访问过，就不在进行下一步操作
                visit[chs[i] - 'a'] = true;
                swap(chs, i, k);
                process(chs, k + 1);
                swap(chs, i, k);
            }
        }
    }
    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
    public static void main(String[] args) {
        String test = "abc";
        char[] chs = test.toCharArray();
        ans = new ArrayList<>();
        process(chs, 0);
        ans.sort(null);
        System.out.println(ans);
    }
}
