import java.util.*;

public class MostSubsequence {

    /**
     * 小马智行笔试第三题，2022年9月16日。
     * 给定一个长度为n的仅由p、o、n、y组成的字符串，每次可以取出一个"pony"子序列。
     * 问最多能取出几个"pony"子序列？
     * n的规模为1000000。
     */

    public static int getMax(String s) {
        Deque<Integer> stackP = new ArrayDeque<>();
        Deque<Integer> stackO = new ArrayDeque<>();
        Deque<Integer> stackN = new ArrayDeque<>();
        Deque<Integer> stackY = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p') {
                stackP.push(i);
            } else if (s.charAt(i) == 'o') {
                stackO.push(i);
            } else if (s.charAt(i) == 'n') {
                stackN.push(i);
            } else {
                stackY.push(i);
            }
        }
        int ans = 0;
        while (!stackY.isEmpty()) {
            int indexY = stackY.poll();
            while (!stackN.isEmpty() && stackN.peek() > indexY) {
                stackN.poll();
            }
            if (stackN.isEmpty()) {
                break;
            }
            int indexN = stackN.poll();
            while (!stackO.isEmpty() && stackO.peek() > indexN) {
                stackO.poll();
            }
            if (stackO.isEmpty()) {
                break;
            }
            int indexO = stackO.poll();
            while (!stackP.isEmpty() && stackP.peek() > indexO) {
                stackP.poll();
            }
            if (stackP.isEmpty()) {
                break;
            }
            ans++;
            stackP.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(getMax(s));
    }
}
