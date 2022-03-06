package com.javatest;

public class IsRotation {
    /**
     * 如果一个字符串为str，把字符串str前面任意的部分挪到后面形成的字符串叫
     * 作str的旋转词。比如str="12345"，str的旋转词有"12345"、"23451"、
     * "34512"、"45123"和"51234"。给定两个字符串a和b，请判断a和b是否互为旋转
     * 词。
     * 比如：
     * a="cdab"，b="abcd"，返回true。
     * a="1ab2"，b="ab12"，返回false。
     * a="2ab1"，b="ab12"，返回true。
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        s1 += s1;
        return getIndexOf(s1, s2) != -1;
    }

    //KMP算法
    public static int getIndexOf(String s, String m) {
        if (m.length() > s.length()) {
            return -1;
        }
        char[] sstr = s.toCharArray();
        char[] mstr = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(mstr);
        while (si < sstr.length && mi < mstr.length) {
            if (sstr[si] == mstr[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == mstr.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] chs) {
        if (chs.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[chs.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int pos = 2;
        while (pos < next.length) {
            if (chs[pos - 1] == chs[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

//    // 生成随机数字字符串
//    public static String generateString(int size) {
//        String s = "";
//        for (int i = 0; i < size; i++) {
//            int num = (int) (Math.random() * 10);
//            s += num;
//        }
//        return s;
//    }

    public static void main(String[] args) {
        String s1 = "HeXiaodie";
        String s2 = "XiaodieHe";
        String s3 = "dieHeXiao";
        String s4 = "dieXiaoHe";
        System.out.println(isRotation(s1, s2));
        System.out.println(isRotation(s2, s3));
        System.out.println(isRotation(s3, s4));
        System.out.println((s1 + s1).contains(s2));
        System.out.println((s3 + s3).contains(s4));
    }
}
