package com.javatest;

public class Rand5ToRand7 {
    /**
     * 给定一个函数f，可以1～5的数字等概率返回一个。请加工出1～7的数字等概率
     * 返回一个的函数g。
     * 给定一个函数f，可以a～b的数字等概率返回一个。请加工出c～d的数字等概率
     * 返回一个的函数g。
     * 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出等概率返回0和1的
     * 函数g
     */
    public static int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }

    public static int rand01() {
        int res = 0;
        do {
            res = rand1To5();
        } while (res == 3);
        return res < 3 ? 0 : 1;
    }

    public static int rand1To7() {
        int res = 0;
        do {
            res = (rand01() << 2) + (rand01() << 1) + rand01();
        } while (res == 7);
        return res + 1;
    }

    public static int rand01p() {
        // you can change p to what you like, but it must be (0,1)
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }

    public static int rand() {
        int res = 0;
        do {
            res = (rand01p() << 1) + rand01p();
        } while (res == 0 || res == 3);
        return res - 1;
    }

    //课程中关于该题的其它官方代码
    public static int rand0To3() {
        return rand01() * 2 + rand01();
    }

    public static int rand1To6() {
        int num = 0;
        do {
            num = rand0To3() * 4 + rand0To3();
        } while (num > 11);
        return num % 6 + 1;
    }

    public static int rand1ToM(int m) {
        return (int) (Math.random() * m) + 1;
    }

    public static int rand1ToN(int n, int m) {
        int[] nMSys = getMSysNum(n - 1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSysNum(randNum, m) + 1;
    }

    public static int[] getMSysNum(int value, int m) {
        int[] res = new int[32];
        int index = res.length - 1;
        while (value != 0) {
            res[index--] = value % m;
            value = value / m;
        }
        return res;
    }

    public static int[] getRanMSysNumLessN(int[] nMSys, int m) {
        int[] res = new int[nMSys.length];
        int start = 0;
        while (nMSys[start] == 0) {
            start++;
        }
        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length) {
            res[index] = rand1ToM(m) - 1;
            if (lastEqual) {
                if (res[index] > nMSys[index]) {
                    index = start;
                    lastEqual = true;
                    continue;
                } else {
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index++;
        }
        return res;
    }

    public static int getNumFromMSysNum(int[] mSysNum, int m) {
        int res = 0;
        for (int i = 0; i != mSysNum.length; i++) {
            res = res * m + mSysNum[i];
        }
        return res;
    }

    public static void printCountArray(int[] countArr) {
        for (int i = 0; i != countArr.length; i++) {
            System.out.println(i + " appears " + countArr[i] + " times");
        }
    }

    public static void main(String[] args) {
        int count1 = 0, count2 = 0;
        for (int i = 0; i < 100000; i++) {
            if (rand() == 0) {
                count1++;
            } else {
                count2++;
            }
        }
        System.out.println(count1 + " " + count2);
        System.out.println("========分割线========");
        int testTimes = 1000000;
        int[] countArr1 = new int[8];
        for (int i = 0; i != testTimes; i++) {
            countArr1[rand1To7()]++;
        }
        printCountArray(countArr1);

        System.out.println("=====================");

        int[] countArr2 = new int[7];
        for (int i = 0; i != testTimes; i++) {
            countArr2[rand1To6()]++;
        }
        printCountArray(countArr2);

        System.out.println("=====================");

        int n = 17;
        int m = 3;
        int[] countArr3 = new int[n + 1];
        for (int i = 0; i != 2000000; i++) {
            countArr3[rand1ToN(n, m)]++;
        }
        printCountArray(countArr3);

        System.out.println("=====================");
    }
}
