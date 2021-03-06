package com.javatest;

import java.util.*;

public class CardsInLine {
    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸
     * 牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A
     * 和玩家B都绝顶聪明。请返回最后获胜者的分数。
     * 【举例】
     * arr=[1,2,100,4]。
     * 开始时，玩家A只能拿走1或4。如果开始时玩家A拿走1，则排列变为[2,100,4]，接下来
     * 玩家 B可以拿走2或4，然后继续轮到玩家A... 如果开始时玩家A拿走4，则排列变为[1,2,100]，接下来玩家B可以拿走1或100，然后继
     * 续轮到玩家A... 玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，
     * 让排列变为[2,100,4]，接下来玩家B不管怎么选，100都会被玩家 A拿走。玩家A会获胜，
     * 分数为101。所以返回101。
     * arr=[1,100,2]。
     * 开始时，玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，
     * 分数为100。所以返回100。
     */
    public static int firstHand(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        return Math.max((arr[l] + secondHand(arr, l + 1, r)), arr[r] + secondHand(arr, l, r - 1));
    }

    public static int secondHand(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(firstHand(arr, l + 1, r), firstHand(arr, l, r - 1));
    }

    public static int Win(int arr[]) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(firstHand(arr, 0, arr.length - 1), secondHand(arr, 0, arr.length - 1));
    }
    public static void main(String[] args) {
        int arr[] = {1000,1,2,100};
        System.out.println(Win(arr));
    }
}
