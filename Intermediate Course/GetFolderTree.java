package com.javatest;

import java.util.HashMap;
import java.util.Map;

public class GetFolderTree {
    /**
     * 给你一个字符串类型的数组arr，譬如：
     * String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
     * 你把这些路径中蕴含的目录结构给画出来，子目录直接列在父目录下面，并比父目录
     * 向右进两格，就像这样:
     * a
     * **b
     * ****c
     * **d
     * ****e
     * b
     * **cst
     * d
     * 同一级的需要按字母顺序排列，不能乱。
     */
    public static class Node {
        public String name;
        public Map<String, Node> nextMap;

        public Node(String name) {
            this.name = name;
            this.nextMap = new HashMap<>();
        }
    }

    public static Node generateFolderTree(String[] folderPaths) {
        Node head = new Node("");
        for (int i = 0; i < folderPaths.length; i++) {
            String[] strs = folderPaths[i].split("\\\\");
            Node cur = head;
            for (String str : strs) {
                if (!cur.nextMap.containsKey(str)) {
                    cur.nextMap.put(str, new Node(str));
                }
                cur = cur.nextMap.get(str);
            }
        }
        return head;
    }

    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }
        Node head = generateFolderTree(folderPaths);
        printProcess(head, 0);
    }

    public static void printProcess(Node head, int level) {
        if (level > 0) {
            System.out.println(get2nSpace(level) + head.name);
        }
        for (Node next : head.nextMap.values()) {
            printProcess(next, level + 1);
        }
    }

    public static String get2nSpace(int level) {
        String res = "";
        for (int i = 1; i < level; i++) {
            res += "  ";
        }
        return res;
    }

    public static void main(String[] args) {
        String[] arr = { "b\\cst", "d\\", "a\\d\\e", "a\\b\\c" };
        print(arr);
    }
}
