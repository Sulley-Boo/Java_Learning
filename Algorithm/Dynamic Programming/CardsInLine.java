package com.javatest;

public class CardsInLine {
    /**
     * 排成一条线的纸牌博弈问题
     * 【题目】
     * 给定一个整型数组 arr，代表数值不同的纸牌排成一条线。玩家 A 和玩家 B 依次拿走每张纸 牌，
     * 规定玩家 A 先拿，玩家 B 后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家 A 和 玩
     * 家 B 都绝顶聪明。请返回最后获胜者的分数。
     * 【举例】
     * arr=[1,2,100,4]。
     * 开始时，玩家 A 只能拿走 1 或 4。如果玩家 A 拿走 1，则排列变为[2,100,4]，接下来玩家 B
     * 可以拿走 2 或 4，然后继续轮到玩家 A。如果开始时玩家 A 拿走 4，则排列变为[1,2,100]，接
     * 下 来玩家 B 可以拿走 1 或 100，然后继续轮到玩家 A。玩家 A 作为绝顶聪明的人不会先拿 4，
     * 因为 拿 4 之后，玩家 B 将拿走 100。所以玩家 A 会先拿 1，让排列变为[2,100,4]，接下来玩
     * 家 B 不管 怎么选，100 都会被玩家 A 拿走。玩家 A 会获胜，分数为 101。所以返回 101。
     * arr=[1,100,2]。
     * 开始时，玩家 A 不管拿 1 还是 2，玩家 B 作为绝顶聪明的人，都会把 100 拿走。玩家 B 会
     * 获胜，分数为 100。所以返回 100
     */

    //方法一:暴力递归求解
    public static int firstHand(int[] arr, int l, int r) {
        //先手函数
        //当前该你拿，arr[l...r]
        //返回你的最好分数
        if (l == r) {
            return arr[l];
        }
        //两种决策，拿最左边的或者最右边的纸牌，然后在(l+1,r)上或者(l,r-1)上后手获得最好分数
        return Math.max((arr[l] + secondHand(arr, l + 1, r)), arr[r] + secondHand(arr, l, r - 1));
    }

    public static int secondHand(int[] arr, int l, int r) {
        //后手函数
        //当前不该你拿，对方在arr[l...r]
        //返回你的最好分数
        if (l == r) {
            return 0;
        }
        //两种决策，对方拿走最左边或者最右边的纸牌，然后在(l+1,r)上或者(l,r-1)上先手，由于对方拿走的是最优选择，我方只能拿最差的选择
        return Math.min(firstHand(arr, l + 1, r), firstHand(arr, l, r - 1));
    }

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(firstHand(arr, 0, arr.length - 1), secondHand(arr, 0, arr.length - 1));
    }

    //方法二:将递归的方法改进，记忆化搜索
    public static int first(int[] arr, int l, int r, int[][] f, int[][] s) {
        if (f[l][r] != -1) {
            return f[l][r];
        }
        if (l == r) {
            f[l][r] = arr[l];
        } else {
            f[l][r] = Math.max((arr[l] + second(arr, l + 1, r, f, s)), arr[r] + second(arr, l, r - 1, f, s));
        }
        return f[l][r];
    }

    public static int second(int[] arr, int l, int r, int[][] f, int[][] s) {
        if (s[l][r] != -1) {
            return s[l][r];
        }
        if (l == r) {
            s[l][r] = 0;
        } else {
            s[l][r] = Math.min(first(arr, l + 1, r, f, s), first(arr, l, r - 1, f, s));
        }
        return s[l][r];
    }

    public static int win2(int[] arr) {
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        if (arr == null || arr.length == 0) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                f[i][j] = -1;
                s[i][j] = -1;
            }
        }
        return Math.max(first(arr, 0, arr.length - 1, f, s), second(arr, 0, arr.length - 1, f, s));
    }

    //方法三:动态规划法
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
//        int[] arr = {1000, 1, 2, 100};
//        int[] arr = {1, 9, 1};
        int[] arr = {1, 2, 100, 4};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));
    }
}
