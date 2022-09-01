import java.util.*;

public class EliminateGame {

    /**
     * 阿里笔试第二题，2022年8月29日
     * 给定n * m棋盘，里面放置四种颜色的棋子，
     * 上下左右相邻的棋子颜色相同则是连通的，
     * 将同色的连通块消除可得到一定的分数。
     * r:1,b:2,g:3,p:5
     * 最多可消除k次，最多可获得多少分？
     */
    public static Map<Character, Integer> map;

    public static class UnionFind {
        private Map<Integer, Integer> fatherMap;
        private Map<Integer, Integer> rankMap;
        private Map<Integer, Integer> weightMap;

        public UnionFind(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            weightMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    fatherMap.put(i * m + j, i * m + j);
                    rankMap.put(i * m + j, 1);
                    weightMap.put(i * m + j, map.get(grid[i][j]));
                }
            }
        }

        public int find(int x) {
            int f = fatherMap.get(x);
            if (f != x) {
                f = find(f);
            }
            fatherMap.put(x, f);
            return f;
        }

        public boolean isSameSet(int a, int b) {
            return find(a) == find(b);
        }

        public void union(int a, int b) {
            int af = fatherMap.get(a);
            int bf = fatherMap.get(b);
            if (af != bf) {
                int small = rankMap.get(af) > rankMap.get(bf) ? bf : af;
                int big = small == af ? bf : af;
                fatherMap.put(small, big);
                weightMap.put(big, weightMap.get(big) + weightMap.get(small));
                weightMap.remove(small);
                rankMap.put(big, rankMap.get(small) + rankMap.get(big));
                rankMap.remove(small);
            }
        }

        public int getBest(int k) {
            Collection<Integer> values = weightMap.values();
            List<Integer> list = new ArrayList<>(values);
            Collections.sort(list, (a, b) -> b - a);
            int res = 0;
            for (int i = 0; i < k; i++) {
                res += list.get(i);
            }
            return res;
        }
    }

    public static void union(char[][] grid, char target, int i, int j, UnionFind uf) {
        int n = grid.length;
        int m = grid[0].length;
        if (i > 0 && grid[i - 1][j] == target) {
            uf.union(i * m + j, (i - 1) * m + j);
        }
        if (i < n - 1 && grid[i + 1][j] == target) {
            uf.union(i * m + j, (i + 1) * m + j);
        }
        if (j > 0 && grid[i][j - 1] == target) {
            uf.union(i * m + j, i * m + j - 1);
        }
        if (j < m - 1 && grid[i][j + 1] == target) {
            uf.union(i * m + j, i * m + j + 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] strs = s.split(" ");
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        int k = Integer.parseInt(strs[2]);
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = str.charAt(j);
            }
        }
        map = new HashMap<>();
        map.put('r', 1);
        map.put('b', 2);
        map.put('g', 3);
        map.put('p', 5);
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char target = grid[i][j];
                union(grid, target, i, j, uf);
            }
        }
        System.out.println(uf.getBest(k));
    }
}

/*
3 3 3
rbr
rrp
bgg

14
 */
