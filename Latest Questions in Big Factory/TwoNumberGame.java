import java.util.*;

public class TwoNumberGame {

    // 网易互联网笔试第一题，2022年8月20日
    // 两个正整数a, b
    // 每次操作可以选择其中一个正整数，删除一个数位
    // 最终希望a是b的倍数或者b是a的倍数
    // 求最少的操作次数
    // 如果无法得到想要的结果返回-1
    public static int minOps(int a, int b) {
        Map<String, Integer> dp = new HashMap<>();
        return process(String.valueOf(a), String.valueOf(b), dp);
    }

    public static int process(String a, String b, Map<String, Integer> dp) {
        String key = a + b;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        if (a.length() == 0 || b.length() == 0) {
            dp.put(key, -1);
        } else if (Integer.parseInt(a) % Integer.parseInt(b) == 0 || Integer.parseInt(b) % Integer.parseInt(a) == 0) {
            dp.put(key, 0);
        } else {
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < a.length(); i++) {
                int result = process(a.substring(0, i) + a.substring(i + 1), b, dp);
                if (result != -1) {
                    ans = Math.min(ans, result + 1);
                }
            }
            for (int i = 0; i < b.length(); i++) {
                int result = process(a, b.substring(0, i) + b.substring(i + 1), dp);
                if (result != -1) {
                    ans = Math.min(ans, result + 1);
                }
            }
            if (ans != Integer.MAX_VALUE) {
                dp.put(key, ans);
            } else {
                dp.put(key, -1);
            }
        }
        return dp.get(key);
    }

    public static void main(String[] args) {
        int a = 111, b = 37;
        System.out.println(minOps(a, b));
        a = 1234;
        b = 99;
        System.out.println(minOps(a, b));
    }

}
