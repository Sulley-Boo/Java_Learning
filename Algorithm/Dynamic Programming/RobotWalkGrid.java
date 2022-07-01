import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RobotWalkGrid {
    /**
     * 有一个机器人处于网格的左上角，网格m行n列，机器人只能向下或者向右移动，
     * 但不能走到一些被禁止的网格（有障碍物）。
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * (1)设计一个算法，返回机器人从走上角走到右下角的路一种可行路径(如果没有可行路径，返回空数组)；
     * (2)设计一个算法，返回从左上角到右下角有多少中走法；
     * (3)设计一个算法，返回从左上角走到右下角的所有可能路径(保证至少有一条可行路径)。
     */

    /*--------(1)--------*/
    // 方法1：回溯法
    public static List<List<Integer>> res;

    public static List<List<Integer>> pathWithObstacles1(int[][] grid) {
        res = new ArrayList<>();
        if (grid == null || grid.length == 0) {
            return res;
        }
        res.add(Arrays.asList(0, 0));
        if (process(grid, 0, 0)) {
            return res;
        } else {
            return new ArrayList<>();
        }
    }

    // 当前在(i, j)位置
    // 返回当前位置是否能走到右下角位置
    public static boolean process(int[][] grid, int i, int j) {
        // 当前位置在网格外，或者在障碍物上，无法走到右下角
        if (i == grid.length || j == grid[0].length || grid[i][j] == 1) {
            return false;
        }
        // 当前位置就在右下角，可以走到右下角
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }
        // 尝试往右走
        res.add(Arrays.asList(i, j + 1));
        if (process(grid, i, j + 1)) {
            return true;
        }
        res.remove(res.size() - 1);
        // 尝试往下走
        res.add(Arrays.asList(i + 1, j));
        if (process(grid, i + 1, j)) {
            return true;
        }
        // 没有return，说明这条路走不通
        res.remove(res.size() - 1);
        grid[i][j] = 1; // 标记当前位置为1，表示当前路走不通，以后不要再走了
        return false;
    }

    // 方法2：动态规划，在动态规划的表结构中回推，获取一种可行结果
    public static List<List<Integer>> pathWithObstacles2(int[][] grid) {
        List<List<Integer>> ans = new ArrayList<>();
        if (grid == null || grid.length == 0) {
            return ans;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return ans;
        }
        boolean[][] dp = new boolean[m][n]; // 返回是否有一条可行路径
        dp[0][0] = true;
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == 1) {
                break;
            }
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            if (grid[0][j] == 1) {
                break;
            }
            dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        if (!dp[m - 1][n - 1]) { // 没有可行结果，返回空数组
            return ans;
        }
        int i = m - 1, j = n - 1;
        // 回推
        while (i > 0 || j > 0) {
            ans.add(Arrays.asList(i, j));
            if (i > 0 && dp[i - 1][j]) { // 往上回推
                i--;
            } else { // 往左回推
                j--;
            }
        }
        ans.add(Arrays.asList(0, 0));
        Collections.reverse(ans);
        return ans;
    }

    /*--------(2)--------*/
    // 动态规划
    // 从右下角往左上角推，最终求dp[0][0]
    public static int uniquePathsWithObstacles1(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            if (grid[m - 1][j] == 1) {
                break;
            }
            dp[m - 1][j] = dp[m - 1][j + 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            if (grid[i][n - 1] == 1) {
                break;
            }
            dp[i][n - 1] = dp[i + 1][n - 1];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                if (grid[i + 1][j] == 1 && grid[i][j + 1] == 1) {
                    dp[i][j] = 0;
                } else if (grid[i + 1][j] == 1) {
                    dp[i][j] = dp[i][j + 1];
                } else if (grid[i][j + 1] == 1) {
                    dp[i][j] = dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // 动态规划
    // 从左上角往右下角推，最终返回dp[m - 1][n - 1]
    public static int uniquePathsWithObstacles2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (grid[i][0] == 1) {
                break;
            }
            dp[i][0] = dp[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            if (grid[0][j] == 1) {
                break;
            }
            dp[0][j] = dp[0][j - 1];
        }
        /*
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1 || (grid[i - 1][j] == 1 && grid[i][j - 1] == 1)) {
                    dp[i][j] = 0;
                } else if (grid[i - 1][j] == 1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (grid[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    // 动态规划
    // 使用了空间压缩
    // 该题的最优解
    public static int uniquePathsWithObstacles3(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    if (j > 0) {
                        dp[j] += dp[j - 1];
                    }
                }
            }
        }
        return dp[n - 1];
    }

    /*--------(3)--------*/
    // 回溯法
    public static List<List<List<Integer>>> ans;
    public static List<List<Integer>> list;

    public static List<List<List<Integer>>> allPathsWithObstacles(int[][] grid) {
        ans = new ArrayList<>();
        list = new ArrayList<>();
        if (grid == null || grid.length == 0) {
            return ans;
        }
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return ans;
        }
        list.add(Arrays.asList(0, 0));
        dfs(grid, 0, 0);
        return ans;
    }

    public static void dfs(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length || grid[i][j] == 1) {
            return;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            ans.add(new ArrayList<>(list));
            return;
        }
        list.add(Arrays.asList(i, j + 1));
        dfs(grid, i, j + 1);
        list.remove(list.size() - 1);
        list.add(Arrays.asList(i + 1, j));
        dfs(grid, i + 1, j);
        list.remove(list.size() - 1);
        grid[i][j] = 1;
    }

    public static void main(String[] args) {
        int[][] m = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(pathWithObstacles1(m));
        System.out.println(pathWithObstacles2(m));
        System.out.println(uniquePathsWithObstacles1(m));
        System.out.println(uniquePathsWithObstacles2(m));
        System.out.println(uniquePathsWithObstacles3(m));
        System.out.println(allPathsWithObstacles(m));
    }

}
