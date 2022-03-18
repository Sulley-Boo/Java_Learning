package com.javatest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestNoRepeatSubstring {
    /**
     * 在一个字符串中找到没有重复字符子串中最长的长度。
     * 例如：
     * abcabcbb没有重复字符的最长子串是abc，长度为3
     * bbbbb，答案是b，长度为1
     * pwwkew，答案是wke，长度是3
     * 要求：答案必须是子串，"pwke" 是一个子字符序列但不是一个子字符串。
     */
    // 动态规划 + 哈希表
    // dp[i]表示必须以i结尾的最长无重复字符子串
    public static int maxUnique1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int[] dp = new int[chs.length];
        dp[0] = 1;
        map.put(chs[0], 0);
        int res = dp[0];
        for (int i = 1; i < chs.length; i++) {
            if (!map.containsKey(chs[i])) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = Math.min(dp[i - 1] + 1, i - map.get(chs[i]));
            }
            map.put(chs[i], i);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 动态规划 + 数组代替哈希表
    public static int maxUnique2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int[] dp = new int[chs.length];
        dp[0] = 1;
        int[] map = new int[256];
        int res = dp[0];
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[chs[0]] = 0;
        for (int i = 1; i < dp.length; i++) {
            if (map[chs[i]] == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = Math.min(dp[i - 1] + 1, i - map[chs[i]]);
            }
            map[chs[i]] = i;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 动态规划(状态压缩) + 数组代替哈希表
    public static int maxUnique3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int cur = 1;
        int[] map = new int[256];
        int res = cur;
        for (int i = 0; i < map.length; i++) {
            map[i] = -1;
        }
        map[chs[0]] = 0;
        for (int i = 1; i < chs.length; i++) {
            if (map[chs[i]] == -1) {
                cur = cur + 1;
            } else {
                cur = Math.min(cur + 1, i - map[chs[i]]);
            }
            map[chs[i]] = i;
            res = Math.max(res, cur);
        }
        return res;
    }

    // 官方代码
    // 瓶颈1：上一次出现chs[i]的位置
    // 瓶颈2：dp[i - 1]的左边界位置
    public static int maxUnique4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chs = s.toCharArray();
        int[] map = new int[256];
        Arrays.fill(map, -1);
        int res = 0;
        int pre = -1; // pre存放两个瓶颈中大的那个的位置
        int cur = 0;
        for (int i = 0; i < chs.length; i++) {
            pre = Math.max(pre, map[chs[i]]);
            cur = i - pre;
            map[chs[i]] = i;
            res = Math.max(res, cur);
        }
        return res;
    }

    // for test
    public static String getRandomString(int len) {
        int size = (int) (Math.random() * len);
        char[] str = new char[size];
        int base = 'a';
        int range = 'z' - 'a' + 1;
        for (int i = 0; i != size; i++) {
            str[i] = (char) ((int) (Math.random() * range) + base);
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(maxUnique1(s));
        System.out.println(maxUnique2(s));
        System.out.println(maxUnique3(s));
        System.out.println(maxUnique4(s));
        int testTime = 10000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            String str = getRandomString(1000);
            if (maxUnique1(str) != maxUnique2(str) || maxUnique2(str) != maxUnique3(str) || maxUnique3(str) != maxUnique4(str)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
