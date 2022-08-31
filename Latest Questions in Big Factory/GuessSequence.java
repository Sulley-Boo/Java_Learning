import java.util.*;

public class GuessSequence {

    /**
     * 2022年8月31日，顺丰Java开发笔试第一题
     * 有一个1 ~ n的数列，需要猜出这个数列是什么。
     * 每次可以猜测某一个位置的数字，会告诉你“大了”，“小了”，“正确”，
     * 在最坏的情况下，需要猜测几次，才能在排列的所有位置得到“正确“的回复。
     * n的规模为10^9
     */

    public static int log(int value, int base) {
        return (int) (Math.log(value) / Math.log(base));
    }

    public static long ways1(int n) {
        long ans = 0;
        int pre = 0;
        long step = 1;
        for (int i = 1; ; i += step) {
            if (i > n) {
                ans += ((long) (log(i, 2) + 1)) * (n - pre);
                break;
            }
            ans += ((long) (log(i, 2) + 1)) * (i - pre);
            step *= 2;
            pre = i;
        }
        return ans;
    }

    public static long ways2(int n) {
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += ((log(i, 2) + 1));
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("测试进行中...");
        for (int i = 1; i < 10000000; i++) {
            if (ways1(i) != ways2(i)) {
                System.out.println("测试失败");
                System.out.println(i);
                break;
            }
        }
        System.out.println(ways1(1000000000)); // 28926258207
    }

}
