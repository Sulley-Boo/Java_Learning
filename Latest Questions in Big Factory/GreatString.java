import java.util.*;

public class GreatString {

    /**
     * 蚂蚁集团笔试题（第三题），2022年9月15日。
     * 一个好串定义为该字符串只有一个字符出现奇数次，其余的字符出现偶数次。
     * 给定一个字符串，长度为n，求该字符串的子串中所有好串的数量。
     * 字符串中全是小写字母。
     * n的规模为200000。
     * <p>
     * 思路：
     * status代表a-z的状态：0-出现偶数次；1-出现奇数次，
     * 如果一个子串满足条件，则status一定为2的幂，
     * 若遍历到i位置的status与之前某个位置j的status的差值为2的幂，则s[j + 1:i]为一个好串，
     * map记录状态为status的s[0:i]的i有多少个。
     * tmp为status中只有一位变号之后的结果，status和tmp的差值必为2的幂，
     * 在map中查找tmp出现的次数，即为以i位置结尾的子串中好串的数量。
     * <p>
     * 该题思路灵感来源于下题：(微软面试题)
     * 字符串s仅由小写字母表示，达标子串定义为该子串中所有的的字符都出现偶数次。
     * 返回s中达标子串的最大长度，n的规模为10^5。
     */

    public static int maxCount(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int status = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        map.put(0, 1);
        for (int i = 0; i < s.length(); i++) {
            status = status ^ (1 << (s.charAt(i) - 'a'));
            for (int j = 0; j < 26; j++) {
                int tmp = status ^ (1 << j);
                if (map.containsKey(tmp)) {
                    ans += map.get(tmp);
                }
            }
            map.put(status, map.getOrDefault(status, 0) + 1);
        }
        return ans;
    }

    public static int maxLength(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = s.length();
        int ans = 0;
        int status = 0;
        for (int i = 0; i < n; i++) {
            status ^= 1 << (s.charAt(i) - 'a');
            if (map.containsKey(status)) {
                ans = Math.max(ans, i - map.get(status));
            } else {
                map.put(status, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(maxCount(s));
        System.out.println(maxLength(s));
    }
}
