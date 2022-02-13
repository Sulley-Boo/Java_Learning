package com.javatest;

import javax.print.DocFlavor;
import java.util.*;

public class HanoiTower {
    /**
     * 汉诺塔问题
     * 打印n层汉诺塔从最左边移动到最右边的全部过程
     * 
     */
    public static void hanoi(int i, String start, String end, String other) {
        if (i == 1) { //base case
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            hanoi(i - 1, start, other, end);
            System.out.println("Move " + i + " from " + start + " to " + end);
            hanoi(i - 1, other, end, start);
        }
    }

    public static void main(String[] args) {
        int n = 3;
        hanoi(n, "左","右","中");
    }
}
