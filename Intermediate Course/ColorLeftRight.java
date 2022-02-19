package com.javatest;

public class ColorLeftRight {
    /**
     * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可
     * 以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将
     * 会被覆盖。牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。
     * 牛牛想知道他最少需要涂染几个正方形。
     * 如样例所示: s = RGRGR
     * 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案。
     *
     * 预处理结构技巧：
     * 如果代码中发现存在大量重复计算，可以提前将这些计算结果存储在一个预处理结构(如数组)中，
     * 使用这些计算结果时直接用查找的方式，从而降低时间复杂度(空间换时间)
     */

    //普通方法求解
    //时间复杂度O(N^2)
    public static int minPaint1(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int res = Integer.MAX_VALUE;
        int paint = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                paint++;
            }
        }
        res = Math.min(res, paint);
        for (int i = 0; i < s.length(); i++) {
            paint = 0;
            for (int l = 0; l <= i; l++) {
                if (s.charAt(l) == 'G') {
                    paint++;
                }
            }
            for (int r = i + 1; r < s.length(); r++) {
                if (s.charAt(r) == 'R') {
                    paint++;
                }
            }
            res = Math.min(res, paint);
        }
        return res;
    }

    //使用预处理方法求解
    //时间复杂度O(N)
    public static int minPaint2(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        int[] right = new int[s.length()]; //预处理数组，记录当前位置往后R的数量(包括当前位置)
        right[s.length() - 1] = s.charAt(s.length() - 1) == 'R' ? 1 : 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            right[i] = s.charAt(i) == 'R' ? right[i + 1] + 1 : right[i + 1];
        }
        int left = 0;
        int res = right[0];
        for (int i = 0; i < s.length() - 1; i++) {
            left = s.charAt(i) == 'G' ? left + 1 : left;
            res = Math.min(res, left + right[i + 1]);
        }
        left = s.charAt(s.length() - 1) == 'G' ? left + 1 : left;
        res = Math.min(res, left);
        return res;
    }

    // for test
    public static String generateRandomString() {
        int maxSize = 1000;
        int size = (int) (Math.random() * maxSize);
        String str = "";
        for (int i = 0;i < size;i++) {
            if ((int) (Math.random() * 2) == 0) {
                str += 'R';
            } else {
                str += 'G';
            }
        }
        return str;
    }

    public static void main(String[] args) {
        String test1 = "RGRGR";
        System.out.println(minPaint1(test1));
        System.out.println(minPaint2(test1));
        String test2 = "GGGGGR";
        System.out.println(minPaint1(test2));
        System.out.println(minPaint2(test2));

        System.out.println("测试进行中...");
        int testTime = 100000;
        for (int i = 0;i < testTime;i++) {
            String test = generateRandomString();
            if (minPaint1(test) != minPaint2(test)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
