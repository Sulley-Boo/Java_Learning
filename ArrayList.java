package com.javatest;

import java.util.*;

public class ArrayList {
    /**
     * ArrayList类
     * 已实现的接口：
     * Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess
     * <p>
     * ArrayList可以存储重复元素，存储的元素有先后顺序。
     *
     * add
     * addAll
     * clear
     * clone
     * contains
     * ensureCapacity
     * get
     * indexOf
     * isEmpty
     * lastIndexOf
     * remove
     * removeAll
     * removeIf
     * replaceAll
     * retainAll
     * set
     * size
     * sort
     * subList
     * toArray
     * trimToSize
     */
    public static void main(String[] args) {
        //初始化
        ArrayList<Integer> list1 = new ArrayList<>(); //初始化方法1
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ArrayList<Integer> list2 = new ArrayList<>(list); //初始化方法2
        ArrayList<Integer> list3 = new ArrayList<>(10); //初始化方法3，指定容量

        //常用方法
        ArrayList<Integer> myList = new ArrayList<>();
        myList.add(1); //将指定的元素追加到此列表的末尾。
        myList.add(3);
        myList.add(4);
        myList.add(1, 2); //将指定元素插入此列表中的指定位置。
        myList.addAll(list); //将指定集合中的所有元素按指定集合的Iterator返回的顺序附加到此列表的末尾。
        myList.addAll(1, list); //从指定位置开始，将指定集合中的所有元素插入此列表。
        myList.clear(); //从此列表中删除所有元素。
        Object myList1 = myList.clone(); //返回此 ArrayList实例的浅表副本。
        myList.add(1);
        myList.add(2);
        myList.add(3);
        System.out.println(myList.contains(1)); //如果此列表包含指定的元素，则返回true。
        myList.ensureCapacity(20); //如有必要，增加此 ArrayList实例的容量，以确保它至少可以容纳由minimum capacity参数指定的元素数。
        System.out.println(myList.get(2)); //返回此列表中指定位置的元素。
        System.out.println(myList.indexOf(4)); //返回此列表中第一次出现的指定元素的索引，如果此列表不包含该元素，则返回-1。
        System.out.println(myList.isEmpty()); //如果此列表不包含任何元素，则返回true。
        System.out.println(myList.lastIndexOf(3)); //返回此列表中指定元素最后一次出现的索引，如果此列表不包含该元素，则返回-1。
        myList.remove(1); //删除此列表中指定位置的元素；从该列表中删除指定元素的第一个匹配项（如果存在）。
        myList.removeAll(list); //从此列表中删除指定集合中包含的所有元素。
        myList.removeIf(i -> i > 2); //删除此集合中满足给定谓词的所有元素。
        myList.replaceAll(i -> i + 2); //将该列表的每个元素替换为将运算符应用于该元素的结果。
        myList.retainAll(list); //仅保留此列表中包含在指定集合中的元素。
        myList.set(1, 5); //用指定的元素替换此列表中指定位置的元素。
        System.out.println(myList.size()); //返回此列表中的元素数。
        myList.sort(new Comparator<Integer>() { //根据指定的 Comparator引发的顺序对此列表进行排序。
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        List<Integer> subList = myList.subList(0, 1); //返回指定的 fromIndex （包含）和 toIndex （独占）之间的此列表部分的视图。
        Object[] arr = myList.toArray(); // 以适当的顺序（从第一个元素到最后一个元素）返回包含此列表中所有元素的数组。
        myList.trimToSize(); //将此 ArrayList实例的容量调整为列表的当前大小。
        Iterator<Integer> it = list.iterator(); //迭代器
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        ListIterator<Integer> lit = list.listIterator(1); //从列表中的指定位置开始，返回列表中元素的列表迭代器（按正确顺序），没有参数表示从0开始。
        while (lit.hasNext()) {
            System.out.println(lit.nextIndex());
        }
    }
}
