import java.util.*;

public class MagicTakeOut {

    /**s
     * 2022年8月13日，美团笔试第一题
     * 给定nums数组，表示有n份外卖需要送，每一份外卖在nums[i]时刻超时。
     * 正常配送外卖需要时间t，使用一次魔法可以不消耗时间送一份外卖。
     * 在0时刻收到了这n份订单，需要保证每一份外卖送达时间小于等于这个超时时间。
     * 求至少需要使用几次魔法来保证每一份外卖不超时。
     */

    // 贪心策略
    // 第一个t，优先送[t, 2t - 1]的外卖，其次送[2t, 3t - 1]的外卖，以此类推
    // 第二个t，优先送[2t, 3t - 1]的外卖，其次送[3t, 4t - 1]的外卖，以此内推
    // ...
    public static int minOps(int[] nums, int t) {
        int max = 0;
        int ans = 0;
        // 有序表中存储第key个t，优先处理的外卖个数
        // 如[5,6,7,8,9,10],t=5
        // map里存放{1=5,2=1}
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            map.put(num / t, map.getOrDefault(num / t, 0) + 1);
            max = Math.max(max, num);
        }
        int cur = t;
        while (cur < max + 1) {
            int num = cur / t;
            Integer next = map.ceilingKey(num); // 找到第num个t优先处理的外卖
            if (next != null) {
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    map.remove(next);
                }
            }
            cur += t;
        }
        // 能处理的外卖已经被处理了，剩下的只能用魔法处理
        for (Integer value : map.values()) {
            ans += value;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(minOps(nums, t));
    }
}
