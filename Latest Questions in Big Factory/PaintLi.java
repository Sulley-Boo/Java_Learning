import java.util.*;

public class PaintLi {

    // 阿里笔试第三题，2022年8月29日
    // 给定n
    // 绘画出一个n * 11行，n * 11列的”里“字
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int index = 0;
        while (index < n) {
            for (int i = 0; i < n * 11; i++) {
                System.out.print(".");
            }
            System.out.println();
            index++;
        }
        while (index < n * 2) {
            for (int i = 0; i < n * 11; i++) {
                if (i < n * 2 || n * 11 - i <= n * 2) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 3) {
            for (int i = 0; i < n * 11; i++) {
                if ((i >= 2 * n && i < 3 * n) || (i >= 5 * n && i < 6 * n)
                        || (i >= 8 * n && i < 9 * n)) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 4) {
            for (int i = 0; i < n * 11; i++) {
                if (i < n * 2 || n * 11 - i <= n * 2) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 5) {
            for (int i = 0; i < n * 11; i++) {
                if ((i >= 2 * n && i < 3 * n) || (i >= 5 * n && i < 6 * n) ||
                        (i >= 8 * n && i < 9 * n)) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 6) {
            for (int i = 0; i < n * 11; i++) {
                if (i < n * 2 || n * 11 - i <= n * 2) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 7) {
            for (int i = 0; i < n * 11; i++) {
                if (i >= 5 * n && i < 6 * n) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 8) {
            for (int i = 0; i < n * 11; i++) {
                if (i < n * 2 || n * 11 - i <= n * 2) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 9) {
            for (int i = 0; i < n * 11; i++) {
                if (i >= 5 * n && i < 6 * n) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
            index++;
        }
        while (index < n * 10) {
            for (int i = 0; i < n * 11; i++) {
                if (i < n * 2 || n * 11 - i <= n * 2) {
                    System.out.print(".");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
            index++;
        }
        while (index < 11 * n) {
            for (int i = 0; i < n * 11; i++) {
                System.out.print(".");
            }
            System.out.println();
            index++;
        }
    }
}

