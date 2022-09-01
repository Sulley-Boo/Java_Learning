import java.util.*;

public class MaxValue {

    /**
     * B站笔试第二题，2022年9月1日。
     * 交换结点求最大权值和。
     * 权值和的定义为每一层的结点权值之和，在最多允许交换一次相邻结点的情况下，
     * 求交换后（可以不交换）能得到的最大权值和。
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int maxValue(TreeNode root) {
        // write code here
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        List<List<TreeNode>> values = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            List<TreeNode> value = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                value.add(cur);
                if (i == size - 1) {
                    sum += cur.val;
                    list.add(sum);
                }
                sum += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            values.add(new ArrayList<>(value));
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
                index = i;
            }
        }
        List<TreeNode> indexLevel = values.get(index);
        List<TreeNode> upLevel = null;
        if (index != 0) {
            upLevel = values.get(index - 1);
        }
        int ans = max;
        for (TreeNode node : indexLevel) {
            if (node.left != null) {
                ans = Math.max(ans, max - node.val + node.left.val);
            }
            if (node.right != null) {
                ans = Math.max(ans, max - node.val + node.right.val);
            }
        }
        if (upLevel != null) {
            for (TreeNode node : upLevel) {
                if (node.left != null) {
                    ans = Math.max(ans, max - node.left.val + node.val);
                }
                if (node.right != null) {
                    ans = Math.max(ans, max - node.right.val + node.val);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(maxValue(root));
    }

}
