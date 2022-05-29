import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CoverMax {

    // 大厂经典面试题
    // 给定一个二维数组lines，代表n条线段
    // lins[i]的长度为2，表示第i条绳子的开始和结束
    // 返回最多线段重合的部分，有多少条线段？

    // 暴力解法
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    // 贪心，使用排序+小根堆
    public static int maxCover2(int[][] lines) {
        Arrays.sort(lines, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int cover = 0;
        for (int[] line : lines) {
            int start = line[0];
            int end = line[1];
            while (!heap.isEmpty() && heap.peek() <= start) {
                heap.poll();
            }
            heap.offer(end);
            cover = Math.max(cover, heap.size());
        }
        return cover;
    }

    // 生成测试用例
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }

    // 测试
    public static void main(String[] args) {
        int testTimes = 100000;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTimes; i++) {
            int[][] arr = generateLines(100, 0, 500);
            int ans1 = maxCover1(arr);
            int ans2 = maxCover2(arr);
            if (ans1 != ans2) {
                System.out.println("测试失败!");
                System.out.println(ans1);
                System.out.println(ans2);
                print(arr);
                break;
            }
        }
    }

}
