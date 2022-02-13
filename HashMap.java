package com.javatest;

import java.util.*;

public class HashMap {
    /*
    * HashMap and HashSet
    * 1.HashMap
    * put()
    * get()
    * entrySet()
    * keySet()
    * isEmpty()
    * containsKey()
    * containValue()
    * putAll()
    * values()
    * size()
    * remove()
    * clone()
    * merge()
    * compute()
    * computeIfAbsent()
    * computeIfPresent()
    *
    * 2.HashSet
    * add()
    * contains()
    * isEmpty()
    * iterator()
    * remove()
    * size()
    * spliterator()
    *
    * */
    public static void main(String[] args) {
        HashMap<Integer, String> hashmap = new HashMap<>();
        HashMap<Integer,String> hashmap1 = new HashMap<>();
        hashmap1.put(4,"d");
        hashmap1.put(5,"e");
        hashmap.put(1,"a");//将指定的值与此映射中的指定键相关联
        hashmap.put(2,"b");
        hashmap.put(2,"c");
        hashmap.put(3,"c");
        System.out.println(hashmap.get(2));//返回指定键映射到的值，如果此映射不包含键的映射，则返回 null
        System.out.println(hashmap.entrySet());//返回此映射中包含的映射的Set视图
        System.out.println(hashmap.keySet());//返回此映射中包含的键的Set视图
        System.out.println(hashmap.isEmpty());//如果此映射不包含键 - 值映射，则返回 true
        System.out.println(hashmap.containsKey(4));//如果此映射包含指定键的映射，则返回 true
        System.out.println(hashmap.containsValue("c"));//如果此映射将一个或多个键映射到指定值，则返回 true
        hashmap.putAll(hashmap1);//将指定映射中的所有映射复制到此映射
        System.out.println(hashmap.values());//返回此映射中包含的值的Collection视图
        System.out.println(hashmap.size());//返回此映射中键 - 值映射的数量
        hashmap.remove(4);//从此映射中删除指定键的映射（如果存在）
        // merge()方法：如果指定的键尚未与值关联或与null关联，则将其与给定的非空值关联
        Object hashmap2 = hashmap1.clone();//返回此 HashMap实例的浅表副本：未克隆键和值本身
        hashmap.merge(6,"f",(oldvalue,newvalue) -> oldvalue + newvalue);//{1=a, 2=c, 3=c, 5=e, 6=f}
        hashmap.merge(6,"g",(oldvalue,newvalue) -> oldvalue + newvalue);//{1=a, 2=c, 3=c, 5=e, 6=fg}
        hashmap.compute(3,(key,value) -> value + "d");//尝试计算指定键及其当前映射值的映射（如果没有当前映射， null）
        String s0 = hashmap.computeIfAbsent(7, key -> "h");//如果指定的键尚未与值关联（或映射到 null），则尝试使用给定的映射函数计算其值并将其输入此映射，除非null（不会为已存在的映射赋值）
        String s1 = hashmap.computeIfPresent(5,(key,value) -> value + "i");//如果指定键的值存在且为非null，则尝试在给定键及其当前映射值的情况下计算新映射
        hashmap1.clear();//从此映射中删除所有映射
        System.out.println(hashmap);
        System.out.println("=================");
        HashSet<Integer> hashset = new HashSet<>();
        hashset.add(1);
        hashset.add(2);
        hashset.add(3);//如果指定的元素尚不存在，则将其添加到此集合中
        System.out.println(hashset.contains(2));//如果此set包含指定的元素，则返回 true
        System.out.println(hashset.isEmpty());//如果此集合不包含任何元素，则返回 true
        Iterator it = hashset.iterator();//返回此set中元素的迭代器
        hashset.remove(3);//如果存在，则从该集合中移除指定的元素
        System.out.println(hashset.size());//返回此集合中的元素数（基数）
        Spliterator<Integer> myspliterator = hashset.spliterator();//在此集合中的元素上创建late-binding和失败快速 Spliterator
        myspliterator.forEachRemaining((n) -> System.out.println(n));
        System.out.println(hashset);
    }
}
