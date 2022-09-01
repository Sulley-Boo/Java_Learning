import java.util.*;

public class Main {

    /**
     * B站笔试第三题，2022年9月1日。
     * 给定一颗二叉树，自底向上切割成若干个平衡二叉树。
     * 切割规则如下：
     * 自底向上切割，当遇到不平衡的结点，切割距离它最近的平衡子树， 切割的次数尽可能少。
     * 切割后的子树按照结点从小到大排列，结点个数相同按照根节点权值从小到大排列。
     */

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static List<TreeNode> list;

    public static class Info {
        public int depth;
        public boolean isBalanced;

        public Info(int depth, boolean isBalanced) {
            this.depth = depth;
            this.isBalanced = isBalanced;
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        boolean isBalanced = false;
        if (Math.abs(leftInfo.depth - rightInfo.depth) <= 1
                && leftInfo.isBalanced && rightInfo.isBalanced) {
            isBalanced = true;
        }
        if (!isBalanced) {
            int leftSize = getSize(root.left);
            int rightSize = getSize(root.right);
            if (leftInfo.isBalanced && leftSize != 1) {
                if (root.left != null) {
                    list.add(root.left);
                    root.left = null;
                }
            }
            if (rightInfo.isBalanced && rightSize != 1) {
                if (root.right != null) {
                    list.add(root.right);
                    root.right = null;
                }
            }
            if (leftSize != 1 && rightSize != 1) {
                return new Info(1, true);
            } else {
                return new Info(2, true);
            }
        }
        return new Info(depth, isBalanced);
    }


    public static int getSize(TreeNode x) {
        if (x == null) {
            return 0;
        }
        return getSize(x.left) + getSize(x.right) + 1;
    }

    public static TreeNode[] balanceSubTree(TreeNode root) {
        // write code here
        list = new ArrayList<>();
        process(root);
        list.add(root);
        list.sort(new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return getSize(o1) - getSize(o2) == 0 ? o1.val - o2.val : getSize(o1) - getSize(o2);
            }
        });
        TreeNode[] ans = new TreeNode[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        TreeNode[] ans = balanceSubTree(root);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i].val);
        }
    }
}
