import java.math.BigInteger;
import java.util.*;

public class BeautifulStr {
    /**
     * 京东笔试第三题，2022年8月27日
     * 求漂亮串的数量
     * 给定n，所有长度为n的字符串中，至少包含两个red的字符串，为漂亮串。
     * 字符串仅由26个小写字符构成。
     * 求长度为n的字符串中漂亮串有多少个？
     * <p>
     * 思路：
     * 状态转移方程：
     * dp1[i]表示长度为i中至少包含一个red的字符串有多少个
     * 1)结尾的字符串是red时，前面i-3个字符可以自由选择26 ^ (i-3)
     * 2)结尾的字符不是red时，
     * (1)最后一个字符不是d，25 * dp1[i - 1]
     * (2)最后一个字符是d，dp1[i - 1] - dp1[i - 3]
     * 因此：dp1[i] = 26 ^ (i-3) + 26 * dp1[i - 1] - dp1[i - 3]
     * <p>
     * dp2[i]表示长度为i中至少包含两个red的字符串有多少个
     * 1)结尾的字符串是red时，前面i-3个字符至少得包含一个red，dp1[i - 3]
     * 2)结尾的字符不是red时，
     * (1)最后一个字符不是d，25 * dp2[i - 1]
     * (2)最后一个字符是d，dp2[i - 1] - dp2[i - 3]
     * 因此：dp2[i] = dp1[i - 3] + 26 * dp2[i - 1] - dp2[i - 3]
     */

    public static int MOD = 1000000007;

    public static long beautifulStr(int n) {
        long[] dp1 = new long[n + 1];
        long[] dp2 = new long[n + 1];
        if (n <= 5) {
            return 0;
        }
        dp1[3] = 1;
        BigInteger mod = new BigInteger(String.valueOf(MOD));
        for (int i = 4; i <= n; i++) {
            // 计算26的(i-3)次幂，然后对MOD取余
            BigInteger bigInteger = new BigInteger("26");
            BigInteger num = bigInteger.pow(i - 3);
            long result = num.mod(mod).longValue();
            // dp1转移方程
            dp1[i] = (result + 26 * dp1[i - 1] - dp1[i - 3] + MOD) % MOD;
        }
        for (int i = 4; i <= n; i++) {
            // dp2转移方程
            dp2[i] = (dp1[i - 3] + 26 * dp2[i - 1] - dp2[i - 3] + MOD) % MOD;
        }
        return dp2[n];
    }

    public static long ways2(int n) {
        BigInteger[] dp1 = new BigInteger[n + 1];
        BigInteger[] dp2 = new BigInteger[n + 1];
        if (n <= 5) {
            return 0;
        }
        dp1[1] = new BigInteger("0");
        dp1[2] = new BigInteger("0");
        dp1[3] = new BigInteger("1");
        BigInteger mod = new BigInteger(String.valueOf(MOD));
        for (int i = 4; i <= n; i++) {
            // 计算26的(i-3)次幂，然后对MOD取余
            BigInteger bigInteger = new BigInteger("26");
            BigInteger num = bigInteger.pow(i - 3);
            // dp1转移方程
            dp1[i] = (num.add(dp1[i - 1].multiply(new BigInteger("26"))).subtract(dp1[i - 3]));
        }
        dp2[1] = new BigInteger("0");
        dp2[2] = new BigInteger("0");
        dp2[3] = new BigInteger("0");
        for (int i = 4; i <= n; i++) {
            // dp2转移方程
            dp2[i] = (dp1[i - 3].add(dp2[i - 1].multiply(new BigInteger("26"))).subtract(dp2[i - 3]));
        }
        return dp2[n].mod(mod).longValue();
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 500; i++) {
            System.out.println(beautifulStr(i) + "," + ways2(i));
        }
    }

}
