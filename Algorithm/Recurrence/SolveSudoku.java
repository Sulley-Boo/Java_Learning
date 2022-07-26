import java.util.*;

public class SolveSudoku {
    
    // 解决世界最难数独
    // 该数独由芬兰数学家因卡拉花费三个月时间设计
    /**
     * 唯一解：
     * 8 1 2 7 5 3 6 4 9 
     * 9 4 3 6 8 2 1 7 5 
     * 6 7 5 4 9 1 2 8 3 
     * 1 5 4 2 3 7 8 9 6 
     * 3 6 9 8 4 5 7 2 1 
     * 2 8 7 1 6 9 5 3 4 
     * 5 2 1 9 7 4 3 6 8 
     * 4 3 8 5 2 6 9 1 7 
     * 7 9 6 3 1 8 4 5 2 
     */
    public static List<int[][]> res = new ArrayList<>();

    private static void init(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int bid = 3 * (i / 3) + j / 3; // 计算i行j列的数字是在哪一个桶里
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bid][num] = true;
                }
            }
        }
    }

    // 当前来到(i, j)位置，依次尝试1~9的数字
    private static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        if (i == 9) { // base case
            res.add(copyArray(board));
            return true;
        }
        // 寻找下一个位置
        int nexti = j == 8 ? i + 1 : i;
        int nextj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') { // 当前位置已经有数字，不用填
            process(board, nexti, nextj, row, col, bucket);
        } else {
            int bit = 3 * (i / 3) + j / 3; // 找到属于哪一个桶
            for (int num = 1; num <= 9; num++) { // 依次尝试1 ~ 9
                if (!row[i][num] && !col[j][num] && !bucket[bit][num]) { //尝试的数字不能冲突
                    row[i][num] = true;
                    col[j][num] = true;
                    bucket[bit][num] = true;
                    board[i][j] = (char) (num + '0');
                    // 尝试下一个位置
                    if (process(board, nexti, nextj, row, col, bucket)) {
                        return true;
                    }
                    // 回溯
                    row[i][num] = false;
                    col[j][num] = false;
                    bucket[bit][num] = false;
                    board[i][j] = '.';
                }
            }
        }
        return false;
    }

    // 复制二维数组
    private static int[][] copyArray(char[][] src) {
        int[][] dest = new int[src.length][src[0].length];
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src[0].length; j++) {
                dest[i][j] = src[i][j] - '0';
            }
        }
        return dest;
    }

    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][] bucket = new boolean[9][10];
        init(board, row, col, bucket); // 初始化原矩阵中的信息
        process(board, 0, 0, row, col, bucket);
    }

    public static void main(String[] args) {
        char[][] board = {
                {'8', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '3', '6', '.', '.', '.', '.', '.'},
                {'.', '7', '.', '.', '9', '.', '2', '.', '.'},
                {'.', '5', '.', '.', '.', '7', '.', '.', '.'},
                {'.', '.', '.', '.', '4', '5', '7', '.', '.'},
                {'.', '.', '.', '1', '.', '.', '.', '3', '.'},
                {'.', '.', '1', '.', '.', '.', '.', '6', '8'},
                {'.', '.', '8', '5', '.', '.', '.', '1', '.'},
                {'.', '9', '.', '.', '.', '.', '4', '.', '.'}
        };
        solveSudoku(board);
        for (int[][] m : res) {
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[0].length; j++) {
                    System.out.print(m[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

}
