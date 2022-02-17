package com.javatest;

import java.util.HashMap;
import java.util.Map;

public class RandomPool {
    /**
     * 设计RandomPool结构
     * 【题目】
     * 设计一种结构，在该结构中有如下三个功能:
     * insert(key):将某个key加入到该结构，做到不重复加入
     * delete(key):将原本在结构中的某个key移除
     * getRandom(): 等概率随机返回结构中的任何一个key。
     * 【要求】
     * Insert、delete和getRandom方法的时间复杂度都是O(1)
     */

    public static class Pool<K> {
        private Map<K, Integer> keyIndexMap;
        private Map<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.indexKeyMap = new HashMap<>();
            this.keyIndexMap = new HashMap<>();
            this.size = 0;
        }

        @Override
        public String toString() {
            return "Pool{" +
                    "indexKeyMap=" + indexKeyMap +
                    '}';
        }

        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.indexKeyMap.put(size, key);
                this.keyIndexMap.put(key, size);
                this.size++;
            }
        }

        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = this.size - 1;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
                this.size--;
            }
        }

        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Pool<String> hxd = new Pool();
        hxd.insert("He");
        hxd.insert("Xiao");
        hxd.insert("Die");
        hxd.insert("Abu");
        hxd.insert("❤");
        System.out.println(hxd);
        hxd.delete("Abu");
        System.out.println(hxd);

        System.out.println(hxd.getRandom());
        System.out.println(hxd.getRandom());
        System.out.println(hxd.getRandom());
        System.out.println(hxd.getRandom());
    }
}
