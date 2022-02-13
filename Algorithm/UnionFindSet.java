package com.javatest;

import java.util.*;

public class UnionFindSet {
    /**
     * 并查集的结构和实现
     */
    public static class Element<V> { //对待处理的元素包装成一个类
        public V value;

        public Element(V value) {
            this.value = value;
        }

    }

    public static class UnionFindSet<V> { //并查集类
        public HashMap<V, Element<V>> elementMap; //存储元素 -> 包装元素
        public HashMap<Element<V>, Element<V>> fatherMap; //存储包装元素 -> 包装元素的父亲元素
        public HashMap<Element<V>, Integer> rankMap; //存储父亲元素，以及包含的元素个数

        public UnionFindSet(List<V> list) { //初始化
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<V>(value); //对每个value包装
                elementMap.put(value, element);
                fatherMap.put(element, element); //初始每个元素的父亲元素是它自己
                rankMap.put(element, 1); //初始每个元素都是父亲元素，个数为1
            }
        }

        private Element<V> findHead(Element<V> element) {
            //查找元素的父亲元素，并实现扁平化（将路径上查找的元素的父亲元素均设置为查找到的父亲元素）
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) { //查找父亲元素
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) { //将路径上的元素均设置好它们的父亲元素
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b) { //判断元素是否在一个集合，即看它们的父亲元素是不是同一个
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) { //合并两个集合为同一个集合
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) { //在不同的集合时才需要合并
                    Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF; //看哪个集合的元素个数多
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big); //将少的父亲元素的父亲设置为多的父亲元素
                    rankMap.put(big, rankMap.get(aF) + rankMap.get(bF)); //更新新的父亲元素的元素个数
                    rankMap.remove(small); //small已不再是父亲元素，将它移除
                }
            }
        }

    }

    public static void main(String[] args) {

    }
}
