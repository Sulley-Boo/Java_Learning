package com.javatest;

import java.util.HashMap;

public class SetAllMap {
    /**
     * 包含SetAll功能的哈希表。
     * 设计一个带有setAll方法的哈希表，主要包含方法：
     * (1)put(key, value)
     * (2)get(key)
     * (3)remove(key)
     * (4)setAll(value)
     * (5)size()
     * (6)isEmpty()
     * 要求所有的方法时间复杂度O(1)。
     */

    public static class Node {
        public int val;
        public long time;

        public Node(int val, long time) {
            this.val = val;
            this.time = time;
        }
    }

    public static class SetAllHashMap {
        private int all;
        private HashMap<Integer, Node> map;
        private long time;
        private long setAllTime;
        private int size;

        public SetAllHashMap() {
            this.all = 0;
            this.map = new HashMap<>();
            this.time = 0;
            this.setAllTime = Long.MIN_VALUE;
            this.size = 0;
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                map.put(key, new Node(value, time));
                time++;
                size++;
            } else {
                map.put(key, new Node(value, time));
                time++;
            }
        }

        public Integer get(int key) {
            if (!map.containsKey(key)) {
                return null;
            }
            long curTime = map.get(key).time;
            if (curTime < setAllTime) {
                return all;
            } else {
                return map.get(key).val;
            }
        }

        public boolean remove(int key) {
            if (!map.containsKey(key)) {
                return false;
            }
            map.remove(key);
            size--;
            return true;
        }

        public void setAll(int value) {
            setAllTime = time;
            all = value;
            time++;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        SetAllHashMap map = new SetAllHashMap();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println(map.get(3));
        map.setAll(10);
        System.out.println(map.get(3));
        map.remove(2);
        System.out.println(map.get(2));
        System.out.println(map.get(1));
        map.put(2, 7);
        map.put(4, 8);
        System.out.println(map.get(2));
        map.setAll(20);
        System.out.println(map.get(3));
        System.out.println(map.get(4));
    }
}
