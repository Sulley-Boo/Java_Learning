import java.util.*;

public class MinCostMostE {
    /**
     * 网易互联网笔试第三题，2022年8月20日。
     * 一个仅含r、e、d的字符串。
     * 定义一个e为好e，当且仅当这个e和r、d相邻，
     * 如"reeder"中有一个好e，前两个e不是好e，第三个e是好e，
     * 每次操作可以将任意字符修改为任意字符，即三种字符可以互相修改，
     * 最终希望好e的数量尽可能多，求最少的修改次数。
     * 字符串长度规模为2*10^5。
     */

    // 方法1，暴力递归
    public static int minCost1(String str) {
        int n = str.length();
        if (n < 3) {
            return -1;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) == 'd' ? 0 : str.charAt(i) == 'e' ? 1 : 2;
        }
        int maxGood = 0;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cost = arr[0] == i ? 0 : 1;
                cost += arr[1] == j ? 0 : 1;
                Info cur = process(arr, 2, j, i);
                if (cur.maxGood > maxGood) {
                    maxGood = cur.maxGood;
                    minCost = cost + cur.minCost;
                } else if (cur.maxGood == maxGood) {
                    minCost = Math.min(minCost, cur.minCost + cost);
                }
            }
        }
        return minCost;
    }

    public static class Info {
        public int maxGood;
        public int minCost;

        public Info(int maxGood, int minCost) {
            this.maxGood = maxGood;
            this.minCost = minCost;
        }
    }

    public static Info process(int[] arr, int i, int pre, int prePre) {
        if (i == arr.length) {
            return new Info(0, 0);
        }
        // 情况1，i位置变0
        int p1Value = pre == 1 && prePre == 2 ? 1 : 0;
        int p1Cost = arr[i] == 0 ? 0 : 1;
        Info info = process(arr, i + 1, 0, pre);
        p1Value += info.maxGood;
        p1Cost += info.minCost;
        // 情况2，i位置变1
        int p2Value = 0;
        int p2Cost = arr[i] == 1 ? 0 : 1;
        info = process(arr, i + 1, 1, pre);
        p2Value += info.maxGood;
        p2Cost += info.minCost;
        // 情况3，i位置变2
        int p3Value = pre == 1 && prePre == 0 ? 1 : 0;
        int p3Cost = arr[i] == 2 ? 0 : 1;
        info = process(arr, i + 1, 2, pre);
        p3Value += info.maxGood;
        p3Cost += info.minCost;
        int maxGood = 0;
        int minCost = Integer.MAX_VALUE;
        if (p1Value > maxGood) {
            maxGood = p1Value;
            minCost = p1Cost;
        } else if (p1Value == maxGood) {
            minCost = Math.min(minCost, p1Cost);
        }
        if (p2Value > maxGood) {
            maxGood = p2Value;
            minCost = p2Cost;
        } else if (p2Value == maxGood) {
            minCost = Math.min(minCost, p2Cost);
        }
        if (p3Value > maxGood) {
            maxGood = p3Value;
            minCost = p3Cost;
        } else if (p3Value == maxGood) {
            minCost = Math.min(minCost, p3Cost);
        }
        return new Info(maxGood, minCost);
    }

    // 方法2，动态规划
    // 时间复杂度O(n * 3 * 3) ≈ O(n)
    // n最大为200000，9*n为1800000，约2*10^6，肯定不会超时
    public static int minCost2(String str) {
        int n = str.length();
        if (n < 3) {
            return -1;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) == 'd' ? 0 : str.charAt(i) == 'e' ? 1 : 2;
        }
        Info[][][] dp = new Info[n + 1][3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dp[n][i][j] = new Info(0, 0);
            }
        }
        for (int i = n - 1; i >= 2; i--) {
            for (int prePre = 0; prePre < 3; prePre++) {
                for (int pre = 0; pre < 3; pre++) {
                    // 情况1，i位置变0
                    int p1Value = pre == 1 && prePre == 2 ? 1 : 0;
                    int p1Cost = arr[i] == 0 ? 0 : 1;
                    Info info = dp[i + 1][0][pre];
                    p1Value += info.maxGood;
                    p1Cost += info.minCost;
                    // 情况2，i位置变1
                    int p2Value = 0;
                    int p2Cost = arr[i] == 1 ? 0 : 1;
                    info = dp[i + 1][1][pre];
                    p2Value += info.maxGood;
                    p2Cost += info.minCost;
                    // 情况3，i位置变2
                    int p3Value = pre == 1 && prePre == 0 ? 1 : 0;
                    int p3Cost = arr[i] == 2 ? 0 : 1;
                    info = dp[i + 1][2][pre];
                    p3Value += info.maxGood;
                    p3Cost += info.minCost;
                    int maxGood = 0;
                    int minCost = Integer.MAX_VALUE;
                    if (p1Value > maxGood) {
                        maxGood = p1Value;
                        minCost = p1Cost;
                    } else if (p1Value == maxGood) {
                        minCost = Math.min(minCost, p1Cost);
                    }
                    if (p2Value > maxGood) {
                        maxGood = p2Value;
                        minCost = p2Cost;
                    } else if (p2Value == maxGood) {
                        minCost = Math.min(minCost, p2Cost);
                    }
                    if (p3Value > maxGood) {
                        maxGood = p3Value;
                        minCost = p3Cost;
                    } else if (p3Value == maxGood) {
                        minCost = Math.min(minCost, p3Cost);
                    }
                    dp[i][pre][prePre] = new Info(maxGood, minCost);
                }
            }
        }
        int maxGood = 0;
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int cost = arr[0] == i ? 0 : 1;
                cost += arr[1] == j ? 0 : 1;
                Info cur = dp[2][j][i];
                if (cur.maxGood > maxGood) {
                    maxGood = cur.maxGood;
                    minCost = cost + cur.minCost;
                } else if (cur.maxGood == maxGood) {
                    minCost = Math.min(minCost, cur.minCost + cost);
                }
            }
        }
        return minCost;
    }

    public static String generateRandom() {
        int size = (int) (Math.random() * 10) + 1;
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
        String str = generateRandom(20);
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
