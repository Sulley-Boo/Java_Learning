package com.javatest;

import java.util.Iterator;
import java.util.LinkedList;

public class Test {
    /*
    * Java LinkedList:
    * Java LinkedList（链表） 类似于 ArrayList，是一种常用的数据容器。
    * 与ArrayList相比，LinkedList 的增加和删除的操作效率更高，而查找和修改的操作效率较低。
    *以下情况使用 ArrayList :
    * 1.频繁访问列表中的某一个元素。
    * 2.只需要在列表末尾进行添加和删除元素操作。
    * 以下情况使用 LinkedList:
    * 你需要通过循环迭代来访问列表中的某些元素.
    * 需要频繁的在列表开头、中间、末尾等位置进行添加和删除元素操作。
    *
    * LinkedList 继承了 AbstractSequentialList 类。
    * LinkedList 实现了 Queue 接口，可作为队列使用。
    * LinkedList 实现了 List 接口，可进行列表的相关操作。
    * LinkedList 实现了 Deque 接口，可作为队列使用。
    * LinkedList 实现了 Cloneable 接口，可实现克隆。
    * LinkedList 实现了 java.io.Serializable 接口，即可支持序列化，能通过序列化去传输。
    *
    * add()
    * addAll()
    * addFirst()
    * addLast()
    * clone()
    * clear()
    * contains()
    * descendingIterator()
    * element()
    * get()
    * getFirst()
    * getLast()
    * indexOf()
    * lastIndexOf()
    * listIterator()
    * offer()
    * offerFirst()
    * offerLast()
    * peek()
    * peekFirst()
    * peekLast()
    * poll()
    * pollFirst()
    * pollLast()
    * pop()
    * push()
    * remove()
    * removeFirst()
    * removeLast()
    * removeFirstOccurrence()
    * removeLastOccurence()
    * set()
    * size()
    * toArray()
    * */
    public static void main(String[] args) {
        LinkedList<Integer> mylist = new LinkedList<Integer>();
        LinkedList<Integer> list0 = new LinkedList<Integer>(){{
            add(4);
            add(5);
            add(6);
        }};
        Iterator it;
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);//将指定元素追加到列表末尾
        mylist.add(1,4);//将指定元素插入列表的指定位置
        mylist.addAll(2,list0);//从指定位置开始，将指定集合中的所有元素插入此列表
        mylist.addAll(list0);//将指定集合中的所有元素按指定集合的迭代器返回的顺序附加到此列表的末尾
        mylist.addFirst(0);//在此列表的开头插入指定的元素
        mylist.addLast(7);//将指定的元素追加到此列表的末尾
        LinkedList<Integer> mylist1 = (LinkedList) mylist.clone();//返回此 LinkedList的浅表副本(元素本身未被克隆)
        mylist.clear();//从此列表中删除所有元素
        System.out.println(mylist1.contains(7));//如果此列表包含指定的元素，则返回 true
        it = mylist1.descendingIterator();//以相反的顺序返回此双端队列中元素的迭代器
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();
        mylist1.element();//检索但不删除此列表的头部（第一个元素）
        System.out.println(mylist1.get(3));//返回此列表中指定位置的元素
        System.out.println(mylist1.getFirst());//返回此列表中的第一个元素
        System.out.println(mylist1.getLast());//返回此列表中的最后一个元素
        System.out.println(mylist1.indexOf(5));//返回此列表中第一次出现的指定元素的索引，如果此列表不包含该元素，则返回-1
        System.out.println(mylist1.lastIndexOf(5));//返回此列表中指定元素最后一次出现的索引，如果此列表不包含该元素，则返回-1
        it = mylist1.listIterator(5);//从列表中的指定位置开始，返回此列表中元素的列表迭代器（按正确顺序）
        while(it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();

        LinkedList<Integer> list1 = new LinkedList<Integer>(){{
            add(1);
            add(2);
            add(3);
        }};
        list1.offer(4);//将指定的元素添加为此列表的尾部（最后一个元素）
        list1.offerFirst(0);//在此列表的前面插入指定的元素
        list1.offerLast(5);//在此列表的末尾插入指定的元素

        list1.peek();//检索但不删除此列表的头部（第一个元素）
        list1.peekFirst();//检索但不删除此列表的第一个元素，如果此列表为空，则返回 null
        list1.peekLast();//检索但不删除此列表的最后一个元素，如果此列表为空，则返回 null
        list1.poll();//检索并删除此列表的头部（第一个元素）
        list1.pollFirst();//检索并删除此列表的第一个元素，如果此列表为空，则返回 null
        list1.pollLast();//检索并删除此列表的最后一个元素，如果此列表为空，则返回 null
        list1.pop();//弹出此列表所代表的堆栈中的元素(第一个元素为栈顶)
        list1.push(2);//将元素推送到此列表所表示的堆栈上

        list1.remove();//检索并删除此列表的头部（第一个元素）
        list1.remove(1);//删除此列表中指定位置的元素
        System.out.println(list1);
        mylist1.removeFirst();//从此列表中删除并返回第一个元素
        mylist1.removeFirstOccurrence(6);//删除此列表中第一次出现的指定元素（从头到尾遍历列表时）
        mylist1.removeLast();//从此列表中删除并返回最后一个元素
        mylist1.removeLastOccurrence(6);//删除此列表中最后一次出现的指定元素（从头到尾遍历列表时）

        mylist1.set(1,3);//用指定的元素替换此列表中指定位置的元素，将第一个元素替换为3
        System.out.println(mylist1.size());//返回此列表中的元素数
        Object[] obj = mylist1.toArray();//以适当的顺序（从第一个元素到最后一个元素）返回包含此列表中所有元素的数组
        for (Object each : obj) {
            System.out.print(each + " ");
        }
        System.out.println();
        System.out.println(mylist1);
    }
}

