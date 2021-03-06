package com.javatest;

public class AppleMinBags {
    /**
     * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个
     * 每袋的包装包装不可拆分。可是小虎现在只想购买恰好n个苹果，小虎想购买尽
     * 量少的袋数方便携带。如果不能购买恰好n个苹果，小虎将不会购买。输入一个
     * 整数n，表示小虎想购买的个苹果，返回最小使用多少袋子。如果无论如何都不
     * 能正好装下，返回-1。
     * <p>
     * 打表法：先用暴力的方法求解，然后对于不同的输入将结果输出统计，
     * 在结果中找规律，从而找出更好的方法
     */

    //普通方法
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple % 8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBagBase6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            bag8--;
            rest = rest + 8;
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest / 6) : -1;
    }

    //打表法
    public static int minBagsAwesome(int apple) {
        if (apple % 2 == 1) {
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ?
                    1 : (apple == 12 || apple == 14 || apple == 16) ?
                    2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }

    public static void main(String[] args) {
//        System.out.println(minBags(100));
//        System.out.println(minBagsAwesome(100));
//        for (int i = 0; i <= 100; i++) {
//            System.out.println(i + ":" + minBags(i));
//        }
        int max = Integer.MAX_VALUE;
        int testTime = 100000000;
        System.out.println("测试进行中...");
        for (int test = 0; test < testTime; test++) {
            int apple = (int) (Math.random() * max);
            if (minBags(apple) != minBagsAwesome(apple)) {
                System.out.println("测试失败!");
                break;
            }
        }
    }
}
