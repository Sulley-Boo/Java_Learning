package com.javatest;

import java.util.*;

public class String {
    /**
     * String类
     * 已实现的接口:
     * ExpressionNode, ExpressionOwner, XPathVisitable, Serializable, SourceLocator
     * <p>
     * length
     * charAt
     * equals
     * compareTo
     * concat
     * getBytes
     * toCharArray
     * <p>
     * StringBuilder类
     * 已实现的接口:
     * Serializable, Appendable, CharSequence
     * <p>
     * append
     * charAt
     * capacity
     * delete
     * deleteCharAt
     * indexOf
     * insert
     * length
     * replace
     * reverse
     * setCharAt
     */
    public static void main(String[] args) {
        String str1 = "abc"; //初始化方法1
        String str2 = new String(); //初始化方法2
        System.out.println(str1.length()); //返回字符串长度
        System.out.println(str1.charAt(0)); //获取字符串指定索引处的字符
        str1.equals(str2); //返回两个字符串是否相等
        str1.compareTo(str2); //比较两个字符串的大小，按照字典序比较，返回1,0,-1
        str1.concat("cd"); //连接指定字符串，相当于+
        str1.getBytes(); //获取字符串的字节数组
        char[] chs = str1.toCharArray(); //将字符串转化为字符数组
        //=============================
        //初始化方法
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder(10);
        StringBuilder sb2 = new StringBuilder(str1);

        //常用方法
        sb.append('a'); //将 char参数的字符串表示形式追加到此序列
        sb.append(str1); //将指定的字符串追加到此字符序列
        sb.append(chs, 0, 1); //将char数组参数的子数组的字符串表示形式追加到此序列
        sb.charAt(1); //返回指定索引处的此序列中的char值
        sb.capacity(); //返回当前容量
        sb.delete(1, 2); //删除此序列的子字符串中的字符
        sb.deleteCharAt(0); //按此顺序删除指定位置的char
        sb.indexOf(str1); //返回指定子字符串第一次出现的字符串中的索引
        sb.indexOf(str1, 1); //从指定的索引处开始，返回指定子字符串第一次出现的字符串中的索引
        sb.insert(1, 'c'); //将char参数的字符串表示形式插入此序列中
        sb.length(); //返回长度（字符数）
        sb.replace(0, 2, str1); //使用指定的 String的字符替换此序列的子字符串中的字符
        sb.reverse(); // 导致此字符序列被序列的反向替换
        sb.setCharAt(0, 'h'); //指定索引处的字符设置为ch
    }
}
