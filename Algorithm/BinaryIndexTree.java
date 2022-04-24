package com.javatest;

public class BinaryIndexTree {

    /**
     * 树状数组代码实现。
     * 注：update方法和query方法的下标索引均从1开始。
     */

    public static class BIT {
        private int MAXN;
        private int[] tree;

        public BIT(int[] nums) {
            this.MAXN = nums.length;
            this.tree = new int[MAXN + 1];
            for (int i = 1; i < tree.length; i++) {
                update(i, nums[i - 1]);
            }
        }

        private int lowbit(int x) {
            return x & (-x);
        }

        private int getSum(int i) {
            int res = 0;
            while (i > 0) {
                res += tree[i];
                i -= lowbit(i);
            }
            return res;
        }

        public void update(int i, int x) {
            while (i <= MAXN) {
                tree[i] += x;
                i += lowbit(i);
            }
        }

        public int query(int a, int b) {
            return getSum(b) - getSum(a - 1);
        }
    }

    // 用于验证树状数组代码的正确性
    public static class Right {
        private int[] nums;

        public Right(int[] arr) {
            this.nums = new int[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                nums[i + 1] = arr[i];
            }
        }

        public void update(int i, int x) {
            nums[i] += x;
        }

        public int query(int a, int b) {
            int res = 0;
            for (int i = a; i <= b; i++) {
                res += nums[i];
            }
            return res;
        }
    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 10000;
        int max = 1000;
        int testTimes = 5000;
        int updateTimes = 2000;
        int queryTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[] test = genarateRandomArray(len, max);
            BIT bit = new BIT(test);
            Right rig = new Right(test);
            for (int j = 0; j < updateTimes; j++) {
                int pos = (int) (Math.random() * test.length) + 1;
                int val = (int) (Math.random() * max) - (int) (Math.random() * max);
                bit.update(pos, val);
                rig.update(pos, val);
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * test.length) + 1;
                int num2 = (int) (Math.random() * test.length) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int res1 = bit.query(L, R);
                int res2 = rig.query(L, R);
                if (res1 != res2) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("测试开始...");
        System.out.println(test() ? "测试成功!" : "测试失败!");
    }
}
