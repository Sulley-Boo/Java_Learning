import java.math.BigInteger;
import java.util.*;

public class RabinKarp {

    public static int getIndexOf(String s1, String s2) {
        if (s2.length() > s1.length()) {
            return -1;
        }
        if (s2.length() == 0) {
            return 0;
        }
        int m = s1.length();
        int n = s2.length();
        BigInteger base = new BigInteger("31");
        BigInteger mod = new BigInteger("1000000007");
        BigInteger encrypt1  = new BigInteger("0");
        BigInteger encrypt2 = new BigInteger("0");
        for (int i = 0; i < n; i++) {
            encrypt1 = (encrypt1.multiply(base).add(new BigInteger(String.valueOf(s1.charAt(i) - 'a')))).mod(mod);
        }
        for (int i = 0; i < n; i++) {
            encrypt2 = (encrypt2.multiply(base).add(new BigInteger(String.valueOf(s2.charAt(i) - 'a')))).mod(mod);
        }
        int index = 0;
        while (index < m - n) {
            if (encrypt1.equals(encrypt2)) {
                return index;
            }
            BigInteger bigInteger = new BigInteger(String.valueOf((s1.charAt(index) - 'a') * (long) Math.pow(31, n - 1)));
            encrypt1 = encrypt1.subtract(bigInteger).mod(mod);
            encrypt1 = ((encrypt1.multiply(base).add(new BigInteger(String.valueOf(s1.charAt(index + n) - 'a'))))).mod(mod);
            index++;
        }
        if (encrypt1.equals(encrypt2)) {
            return index;
        }
        return -1;
    }
  
}

