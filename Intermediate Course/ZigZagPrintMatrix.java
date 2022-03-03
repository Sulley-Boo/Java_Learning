package com.javatest;

public class ZigZagPrintMatrix {
    /**
     * 用zigzag的方式打印矩阵，比如如下的矩阵
     * 0 1 2 3
     * 4 5 6 7
     * 8 9 10 11
     * 打印顺序为：0 1 4 8 5 2 3 6 9 10 7 11
     * @param matrix
     */
    public static void printMatrixZigZag(int[][] matrix) {
        int dR = 0;
        int dC = 0;
        int tR = 0;
        int tC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (tR != endR + 1) {
            printLevel(matrix, dR, dC, tR, tC, fromUp);
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    public static void printLevel(int[][] matrix, int dR, int dC, int tR, int tC, boolean fromUp) {
        if (!fromUp) {
            while (dR != tR - 1) {
                System.out.print(matrix[dR--][dC++] + " ");
            }
        } else {
            while (tR != dR + 1) {
                System.out.print(matrix[tR++][tC--] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);
    }
}
