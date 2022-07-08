import java.util.*;

/**
 * 2022年7月2日字节跳动夏令营项目实习笔试第三题
 * 输入一个nums，代表有n个成员，nums[i]代表第i名成员的魅力值，
 * 每一轮随机抽取两名成员参与活动，活动规则如下：
 * (1) 参加活动的两个人魅力值+2，并且两人(或两人所处的团队)合并为一个团队；
 * (2) 如果某团队中指定A来参加活动，但是A不是团队中魅力值最低的，那么由A所在团队中魅力值最低的B来参加活动；
 * (3) 如果选取的两人在同一个团队中，则没有魅力值变动，此轮无效；
 * (4) 如果团队中有多个魅力值最低的成员，则在其中随机选取一名参与活动。
 * 数组x和y代表一共有m轮活动，每一轮选取的成员编号为x[i], y[i]，均从1开始编号。
 * 求出经过m轮介绍活动后，所有成员中的魅力最低值为多少？
 */
public class Introduction {
    public static class Member {
        public int val; // 编号
        public int charm; // 魅力值

        public Member(int val, int charm) {
            this.val = val;
            this.charm = charm;
        }
    }

    public static class UnionFind {
        private Map<Member, Member> fatherMap; // 记录每个结点的头结点
        private Map<Member, Integer> rankMap; // 记录每个头结点有多少个成员
        private Map<Member, List<Member>> listMap; // 记录头结点的成员列表

        public UnionFind(Member[] members) { // 构造函数
            this.fatherMap = new HashMap<>();
            this.rankMap = new HashMap<>();
            this.listMap = new HashMap<>();
            for (Member member : members) {
                List<Member> list = new ArrayList<>();
                fatherMap.put(member, member);
                rankMap.put(member, 1);
                list.add(member);
                listMap.put(member, list);
            }
        }

        public Member find(Member m) { // 查找头结点
            Member f = fatherMap.get(m);
            if (f != m) {
                f = find(f);
            }
            fatherMap.put(m, f);
            return f;
        }

        public boolean isSameSet(Member m1, Member m2) { // 查看两个成员是否在同一集合
            return find(m1) == find(m2);
        }

        public void union(Member m1, Member m2) { // 合并两个成员
            Member m1F = fatherMap.get(m1);
            Member m2F = fatherMap.get(m2);
            if (m1F != m2F) {
                Member big = rankMap.get(m1F) > rankMap.get(m2F) ? m1F : m2F;
                Member small = big == m1F ? m2F : m1F;
                fatherMap.put(small, big);
                rankMap.put(big, rankMap.get(big) + rankMap.get(small));
                rankMap.remove(small);
                listMap.get(big).addAll(listMap.get(small));
                listMap.remove(small);
            }
        }

        public Member getLeast(Member m) { // 查找某个头结点中，魅力值最低的结点
            List<Member> list = listMap.get(m);
            Collections.sort(list, (m1, m2) -> m1.charm - m2.charm);
            return list.get(0);
        }
    }

    public static int getMinCharm(int[] nums, int[] x, int[] y) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        Member[] members = new Member[n];
        Map<Integer, Member> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            members[i] = new Member(i, nums[i]);
            map.put(i, members[i]);
        }
        UnionFind uf = new UnionFind(members);
        int m = x.length;
        for (int i = 0; i < m; i++) {
            Member m1 = map.get(x[i] - 1);
            Member m2 = map.get(y[i] - 1);
            if (!uf.isSameSet(m1, m2)) { // 不在同一个团队中
                Member m1f = uf.find(m1);
                Member m2f = uf.find(m2);
                // 找到两个成员所在团队的最小魅力值成员，各自魅力值+2
                Member small1 = uf.getLeast(m1f);
                Member small2 = uf.getLeast(m2f);
                small1.charm += 2;
                small2.charm += 2;
                uf.union(m1, m2); // 合并两个团队
            }
        }
        // 经过m轮介绍活动后，遍历得到魅力最低值
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < members.length; i++) {
            ans = Math.min(ans, members[i].charm);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 8, 5, 12, 15};
        int[] x = {1, 2, 2, 3, 1};
        int[] y = {2, 3, 4, 4, 5};
        System.out.println(getMinCharm(nums, x, y));
    }
}
