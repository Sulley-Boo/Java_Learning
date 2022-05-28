import java.math.BigInteger;
import java.util.*;

public class RabinKarp {
    
    /**
     * Rabin-Karp算法实现字符串的匹配问题。
     *
     * Rabin-Karp算法的原理和实现。
     * Rabin-Karp 字符串编码是一种将字符串映射成整数的编码方式，可以看成是一种哈希算法。
     * 具体地，假设字符串包含的字符种类不超过E，那么我们选一个大于等于E的整数base，
     * 就可以将字符串看成base进制的整数，将其转换成十进制数后，就得到了字符串对应的编码。
     * 我们注意到：两个字符串 ss 和 tt 相等，当且仅当它们的长度相等且编码值相等。
     *
     * 注：当字符串很长时，对应的编码值会非常大，使用Integer类型或者Long类型无法存储，
     * 可以使用BigInteger类型来存储，并通过取模的方式来降低编码值，但是取模可能会导致哈希冲突，
     * 因此，对于base和mod值的选取非常重要。
     */

    public static int getIndexOf(String s1, String s2) {
        if (s2.length() > s1.length()) {
            return -1;
        }
        if (s2.length() == 0) {
            return 0;
        }
        int m = s1.length();
        int n = s2.length();
        BigInteger base = new BigInteger("31"); // 小写字母一共26个，这里base取31
        BigInteger mod = new BigInteger("1000000007"); // mod取10 ^ 9 + 7
        BigInteger encrypt1  = new BigInteger("0"); // 字符串s1长度为n的编码值，可变
        BigInteger encrypt2 = new BigInteger("0"); // 字符串s2的编码值，固定
        for (int i = 0; i < n; i++) { // 计算s1子串0 ~ n-1的编码值
            encrypt1 = (encrypt1.multiply(base).add(new BigInteger(String.valueOf(s1.charAt(i) - 'a')))).mod(mod);
        }
        for (int i = 0; i < n; i++) {
            encrypt2 = (encrypt2.multiply(base).add(new BigInteger(String.valueOf(s2.charAt(i) - 'a')))).mod(mod);
        }
        int index = 0;
        while (index < m - n) { // 判断索引位置0 ~ m-n-1是否匹配
            if (encrypt1.equals(encrypt2)) {
                return index;
            }
            // encrypt1去掉头部字符的编码，加上尾部字符的编码
            BigInteger bigInteger = new BigInteger(String.valueOf((s1.charAt(index) - 'a') * (long) Math.pow(31, n - 1)));
            encrypt1 = encrypt1.subtract(bigInteger.mod(mod));
            encrypt1 = (encrypt1.multiply(base).add(new BigInteger(String.valueOf(s1.charAt(index + n) - 'a')))).mod(mod);
            index++;
        }
        if (encrypt1.equals(encrypt2)) { // 判断索引位置m-n是否匹配
            return index;
        }
        return -1;
    }
  
}

