import java.util.*;

public class FinishTime {

    /**
     * 来自美团
     * 给定一个n，表示0~n-1号任务
     * 给定一维数组times，表示每个人物各自完成时间
     * 给定二维数组matrix，matrix[i] = {a, b}，表示要想做a号任务，必须先做b号任务
     * 各个任务如果没有依赖，可以并行完成
     * 返回一个数组res，表示每个任务的完成时间
     */
    public static class Node {
        public int val;
        public int in;
        public List<Node> nexts;

        public Node(int val) {
            this.val = val;
            this.in = 0;
            this.nexts = new ArrayList<>();
        }
    }

    public static class Graph {
        public Map<Integer, Node> nodes;

        public Graph() {
            this.nodes = new HashMap<>();
        }
    }

    public static int[] finishTime(int[] times, int[][] matrix) {
        Graph graph = new Graph();
        int n = times.length;
        for (int i = 0; i < n; i++) {
            graph.nodes.put(i, new Node(i));
        }
        for (int i = 0; i < matrix.length; i++) {
            int from = matrix[i][1];
            int to = matrix[i][0];
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            toNode.in++;
            fromNode.nexts.add(toNode);
        }

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> map = new HashMap<>();
        int[] res = new int[n];
        for (Node node : graph.nodes.values()) {
            map.put(node, node.in);
            if (node.in == 0) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            res[cur.val] += times[cur.val];
            for (Node next : cur.nexts) {
                res[next.val] = Math.max(res[next.val], res[cur.val]);
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] times = {5, 3, 4, 2, 6, 5};
        int[][] matrix = {{1, 0}, {3, 1}, {3, 2}, {4, 2}, {5, 4}};
        int[] res = finishTime(times, matrix);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
