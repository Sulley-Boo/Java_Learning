package com.javatest;

public class EatGrass {
    /**
     * 牛牛和羊羊都很喜欢青草。今天他们决定玩青草游戏。
     * 最初有一个装有n份青草的箱子,牛牛和羊羊依次进行,牛牛先开始。在每个回合中,每个
     * 玩家必须吃一些箱子中的青草,所吃的青草份数必须是4的x次幂,比如1,4,16,64等等。
     * 不能在箱子中吃到有效份数青草的玩家落败。假定牛牛和羊羊都是按照最佳方法进行游
     * 戏,请输出胜利者的名字。
     */

    //递归方法求解
    public static String winner1(int n) {
        //暴力方法求解
        // 0  1  2  3  4
        // 后 先 后 先 先
        if (n < 5) { //base case
            return (n == 0 || n == 2) ? "羊羊" : "牛牛";
        }
        int base = 1; //先手决定吃的草
        while (base <= n) {
            //当前有n份草，先手吃掉base份，n - base份留给后手
            if (winner1(n - base).equals("羊羊")) {
                return "牛牛";
            }
            if (base > n / 4) { //防止base*4之后溢出
                break;
            }
            base *= 4;
        }
        return "羊羊";
    }

    //打表法
    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "羊羊";
        }
        return "牛牛";
    }

    public static void main(String[] args) {
//        for (int i = 0;i <= 100;i++) {
//            System.out.println(i + ":" +winner1(i));
//        }
        System.out.println(winner1(68));
        System.out.println(winner2(68));
    }
}
