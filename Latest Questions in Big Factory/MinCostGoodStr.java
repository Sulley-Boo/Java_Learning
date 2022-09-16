import java.util.*;

public class MinCostGoodStr {

    /**
     * 2022年9月9日，京东笔试第二道。
     * 给定一个仅由r、e、d组成的字符串。
     * 定义一个字符串为"好串"，当且仅当相邻的两个字符都不相同。
     * 一个操作：将相邻的两个相同字符同时删除，并在其位置生成任意一个字符(r、e、d中的一个)。
     * 如"dedd"，可以选择"dd"，将其变成"r"，得到"der"。
     * 给定一个字符串，求将该字符串变成"好串"最少需要多少次操作？
     * 字符串长度规模：200000
     */

    public static int process(int[] arr, int i, int pre, int prePre) {
        if (i == arr.length) {
            if (pre == prePre) {
                return 1;
            }
            return 0;
        }
        int ans = 0;
        if (arr[i] == pre && arr[i] == prePre) { // 连续三个字符都相同，可以处理前两个字符或者后两个字符
            int p1 = process(arr, i + 1, 0, prePre) + 1;
            int p2 = process(arr, i + 1, 1, prePre) + 1;
            int p3 = process(arr, i + 1, 2, prePre) + 1;
            int p4 = process(arr, i + 1, arr[i], 0) + 1;
            int p5 = process(arr, i + 1, arr[i], 1) + 1;
            int p6 = process(arr, i + 1, arr[i], 2) + 1;
            ans = Math.min(p1, Math.min(p2, Math.min(p3, Math.min(p4, Math.min(p5, p6)))));
        } else if (arr[i] != pre && pre == prePre) { // 前两个字符相同，后一个与前面两个不相同，必须处理前两个字符
            int p1 = process(arr, i + 1, arr[i], 0) + 1;
            int p2 = process(arr, i + 1, arr[i], 1) + 1;
            int p3 = process(arr, i + 1, arr[i], 2) + 1;
            ans = Math.min(p1, Math.min(p2, p3));
        } else { // 其他情况，不需要处理
            ans = process(arr, i + 1, arr[i], pre);
        }
        return ans;
    }

    public static int minCost1(String str) {
        int n = str.length();
        int[] arr = new int[n];
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return str.charAt(0) == str.charAt(1) ? 1 : 0;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) == 'd' ? 0 : str.charAt(i) == 'e' ? 1 : 2;
        }
        return process(arr, 2, arr[1], arr[0]);
    }

    public static int minCost2(String str) {
        int n = str.length();
        int[] arr = new int[n];
        if (n == 0 || n == 1) {
            return 0;
        }
        if (n == 2) {
            return str.charAt(0) == str.charAt(1) ? 1 : 0;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) == 'd' ? 0 : str.charAt(i) == 'e' ? 1 : 2;
        }
        int[][][] dp = new int[n + 1][3][3];
        for (int i = 0; i < 3; i++) {
            dp[n][i][i] = 1;
        }
        for (int i = n - 1; i >= 2; i--) {
            for (int pre = 0; pre < 3; pre++) {
                for (int prePre = 0; prePre < 3; prePre++) {
                    if (arr[i] == pre && arr[i] == prePre) {
                        int p1 = dp[i + 1][0][prePre] + 1;
                        int p2 = dp[i + 1][1][prePre] + 1;
                        int p3 = dp[i + 1][2][prePre] + 1;
                        int p4 = dp[i + 1][arr[i]][0] + 1;
                        int p5 = dp[i + 1][arr[i]][1] + 1;
                        int p6 = dp[i + 1][arr[i]][2] + 1;
                        dp[i][pre][prePre] = Math.min(p1, Math.min(p2, Math.min(p3, Math.min(p4, Math.min(p5, p6)))));
                    } else if (arr[i] != pre && pre == prePre) {
                        int p1 = dp[i + 1][arr[i]][0] + 1;
                        int p2 = dp[i + 1][arr[i]][1] + 1;
                        int p3 = dp[i + 1][arr[i]][2] + 1;
                        dp[i][pre][prePre] = Math.min(p1, Math.min(p2, p3));
                    } else {
                        dp[i][pre][prePre] = dp[i + 1][arr[i]][pre];
                    }
                }
            }
        }
        return dp[2][arr[1]][arr[0]];
    }

    public static String generateRandom() {
        int size = (int) (Math.random() * 20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * 3);
            if (random == 0) {
                builder.append("r");
            } else if (random == 1) {
                builder.append("e");
            } else {
                builder.append("d");
            }
        }
        return builder.toString();
    }

    public static String generateRandom(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int random = (int) (Math.random() * 3);
            if (random == 0) {
                builder.append("r");
            } else if (random == 1) {
                builder.append("e");
            } else {
                builder.append("d");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
//        System.out.println(minCost1(s));
//        System.out.println(minCost2(s));
        int testTime = 10000;
        boolean flag = true;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            String s = generateRandom();
            if (minCost1(s) != minCost2(s)) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "测试成功!" : "测试失败!");
        System.out.println("======");
        System.out.println("方法1和方法2的耗时对比:");
        String str = generateRandom(36);
        long start = System.currentTimeMillis();
        System.out.println(minCost2(str));
        long end = System.currentTimeMillis();
        System.out.println("方法2用时:" + (end - start) + "毫秒");
        start = System.currentTimeMillis();
        System.out.println(minCost1(str));
        end = System.currentTimeMillis();
        System.out.println("方法1用时:" + (end - start) + "毫秒");
        System.out.println("======");
        System.out.println("测试一个长度规模很大的字符串:");
        str = generateRandom(200000);
        start = System.currentTimeMillis();
        System.out.println(minCost2(str));
        end = System.currentTimeMillis();
        System.out.println("方法2用时:" + (end - start) + "毫秒");
    }
}
