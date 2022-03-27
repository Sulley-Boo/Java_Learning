package com.javatest;

public class Threetimes {
    /**
     * 小KQM得P算到一法个扩神展奇题的目数二列: 1, 12, 123,...12345678910,1234567891011...。
     * 并且小Q对于能否被3整除这个性质很感兴趣。
     * 小Q现在希望你能帮他计算一下从数列的第l个到第r个(包含端点)有多少个数可以被3整除。
     * 输入描述：
     * 输入包括两个整数l和r(1 <= l <= r <= 1e9), 表示要求解的区间两端。
     * 输出描述：
     * 输出一个整数, 表示区间内能被3整除的数字个数。
     * 示例1:
     * 输入
     * 2 5
     * 输出
     * 3
     */
    public static int getNum(int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            long sum = (long) (i + 1) * i / 2L;
            if (sum % 3 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(getNum(2, 5));
        System.out.println(getNum(6546, 765567));
    }
}
