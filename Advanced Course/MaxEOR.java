public class MaxEOR {
    /**
     * 数组异或和的定义:把数组中所有的数异或起来得到的值
     * 给定一个整型数组 arr，其中可能有正、有负、有零，求其中子数组的最大异或和
     * 【举例】
     * arr = {3}
     * 数组只有1个数，所以只有一个子数组，就是这个数组本身，最大异或和为3
     * arr = {3, -28, -29, 2}
     * 子数组有很多，但是{-28, -29}这个子数组的异或和为7，是所有子数组中最大的
     */

    // 方法1，暴力解法
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int eor = 0;
            for (int j = i; j < arr.length; j++) {
                eor ^= arr[j];
                max = Math.max(max, eor);
            }
        }
        return max;
    }

    // 方法2，使用前缀树
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((num >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);
                best = cur.nexts[best] != null ? best : (best ^ 1);
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test = {3, -28, -29, 2};
        System.out.println(maxXorSubarray2(test));
        int testTime = 50000;
        int maxSize = 1000;
        int maxValue = 500;
        boolean succeed = true;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (maxXorSubarray1(arr) != maxXorSubarray2(arr)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "测试成功!" : "测试失败!");
    }
}
