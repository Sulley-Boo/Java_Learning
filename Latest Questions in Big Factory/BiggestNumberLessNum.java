import java.util.Arrays;

public class BiggestNumberLessNum {

    // 方法1，回溯法，时间复杂度较高
    public static int ans;

    public static void dfs(int[] arr, int i, int sum, int n, int num) {
        if (i == n) {
            if (sum < num) {
                ans = Math.max(ans, sum);
            }
            return;
        }
        for (int k = 0; k < arr.length; k++) {
            if (sum * 10 + arr[k] > num) {
                return;
            }
            dfs(arr, i + 1, sum * 10 + arr[k], n, num);
        }
    }

    public static int maxNumber1(int[] arr, int num) {
        Arrays.sort(arr);
        int n = String.valueOf(num).length();
        ans = -1;
        dfs(arr, 0, 0, n, num);
        if (ans == -1) {
            ans = 0;
            for (int i = 0; i < n - 1; i++) {
                ans = ans * 10 + arr[arr.length - 1];
            }
        }
        return ans;
    }

    // 方法2，贪心法，从高位向地位依次尝试最优的数字
    public static int maxNumber2(int[] arr, int limit) {
        Arrays.sort(arr);
        limit--;
        int offset = 1;
        while (offset <= limit / 10) {
            offset *= 10;
        }
        int ans = process(arr, limit, offset);
        if (ans != -1) {
            return ans;
        } else { // ans = -1 一定要减少位数，才能做到
            offset /= 10;
            int rest = 0;
            while (offset > 0) {
                rest += arr[arr.length - 1] * offset;
                offset /= 10;
            }
            return rest;
        }
    }

    public static int process(int[] arr, int limit, int offset) {
        // offset == 0，意味着，之前的数字，全都可以追平，返回limit
        // 之前的数字和limit完全一样，且limit所有数字都一样
        if (offset == 0) {
            return limit;
        }
        // limit中还有数字，没遍历到，
        // (limit / offset) % 10
        // 当前数字是谁，提取出来
        int cur = (limit / offset) % 10;
        int near = near(arr, cur);
        if (near == -1) {
            return -1;
        } else if (arr[near] == cur) { // 找出来的数字，和当前数字cur一样!
            // 当前位，追平了！
            // 看看后续能不能走的通！
            int ans = process(arr, limit, offset / 10);
            // 1) 走通了！
            // 2) 走不通，
            // A ：当前位还有下降的空间！
            // B ：当前位没有下降的空间！
            if (ans != -1) {
                return ans;
            } else if (near > 0) { // 虽然后续没成功，但是我自己还能下降！还能选更小的数字
                near--;
                return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
            } else { // 后续没成功，我自己也不能再下降了！宣告失败，往上返回！
                return -1;
            }
        } else { // arr[near] < cur
            return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
        }
    }

    // 比如offset = 100
    // 一共3位数
    // 那么就把arr中最大的数字x，拼成xxx，返回
    // 比如offset = 10000
    // 一共5位数
    // 那么就把arr中最大的数字x，拼成xxxxx，返回
    public static int rest(int[] arr, int offset) {
        int rest = 0;
        while (offset > 0) {
            rest += arr[arr.length - 1] * offset;
            offset /= 10;
        }
        return rest;
    }

    // 在有序数组arr中，找到<=num，且最大的数字，在arr中的位置返回
    // 如果所有数字都大于num，返回-1
    public static int near(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int near = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] <= num) {
                near = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return near;
    }

    // for test
    public static int[] randomArray() {
        int[] arr = new int[(int) (Math.random() * 10) + 1];
        boolean[] cnt = new boolean[10];
        for (int i = 0; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * 10);
            } while (cnt[arr[i]]);
            cnt[arr[i]] = true;
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {

        int[] arr = {4, 5, 6, 7, 8, 9};
        int num = 6628;
        System.out.println(maxNumber1(arr, num));
        System.out.println(maxNumber2(arr, num));

        int testTimes = 100000;
        boolean succeed = true;
        System.out.println("测试进行中...");
        for (int i = 0; i < testTimes; i++) {
            int[] nums = randomArray();
            int max = (int) (Math.random() * 100000) + 1;
            int ans1 = maxNumber1(nums, max);
            int ans2 = maxNumber2(nums, max);
            if (ans1 != ans2) {
                succeed = false;
                printArray(nums);
                System.out.println(max);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println(succeed ? "测试成功" : "测试失败");
    }

}
