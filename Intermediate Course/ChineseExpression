package com.javatest;

public class ChineseExpression {
    /**
     * 把一个数字用中文表示出来。
     */
    public static String num1To9(int num) {
        String[] names = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
        if (num < 1 || num > 9) {
            return "";
        }
        return names[num - 1];
    }

    public static String num1To99(int num, boolean hasBai) {
        String res = "";
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 10) {
            return num1To9(num);
        }
        int shi = num / 10;
        if (shi == 1 && !hasBai) {
            res += ("十" + num1To9(num % 10));
        } else {
            res += (num1To9(shi) + "十" + num1To9(num % 10));
        }
        return res;
    }

    public static String num1To999(int num) {
        String res = "";
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return num1To99(num, false);
        }
        int bai = num / 100;
        res += (num1To9(bai) + "百");
        int rest = num % 100;
        if (rest == 0) {
            return res;
        } else if (rest >= 10) {
            res += num1To99(rest, true);
        } else {
            res += ("零" + num1To9(rest));
        }
        return res;
    }

    public static String num1To9999(int num) {
        String res = "";
        if (num < 1 || num > 9999) {
            return "";
        }
        if (num < 1000) {
            return num1To999(num);
        }
        int qian = num / 1000;
        res += (num1To9(qian) + "千");
        int rest = num % 1000;
        if (rest == 0) {
            return res;
        } else if (rest < 10) {
            res += ("零" + num1To9(rest));
        } else if (rest >= 10 && rest < 100) {
            res += ("零" + num1To99(rest, true));
        } else {
            res += num1To999(rest);
        }
        return res;
    }

    public static String num1To99999999(int num) {
        String res = "";
        if (num < 1 || num > 99999999) {
            return "";
        }
        if (num < 10000) {
            return num1To9999(num);
        }
        int wan = num / 10000;
        res += (num1To9999(wan) + "万");
        int rest = num % 10000;
        if (rest == 0) {
            return res;
        } else if (rest < 1000) {
            res += ("零" + num1To999(rest));
        } else {
            res += num1To9999(rest);
        }
        return res;
    }

    // 请保证输入的数在[Integer.MIN_VALUE, Integer.MAX_VALUE]之间
    public static String getNumChiExp(int num) {
        if (num == 0) {
            return "零";
        }
        String res = num < 0 ? "负" : "";
        int yi = Math.abs(num / 100000000);
        if (yi != 0) {
            res += num1To999(yi) + "亿";
        }
        int rest = Math.abs(num % 100000000);
        if (rest == 0) {
            return res;
        } else if (rest < 10000000) {
            res += ("零" + num1To99999999(rest));
        } else {
            res += num1To99999999(rest);
        }
        return res;
    }

    // for test
    public static int generateRandomNum() {
        boolean isNeg = !(Math.random() > 0.5);
        int value = (int) (Math.random() * Integer.MIN_VALUE);
        return isNeg ? value : -value;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int num = generateRandomNum();
            System.out.println(num);
            System.out.println(getNumChiExp(num));
        }
    }
}
