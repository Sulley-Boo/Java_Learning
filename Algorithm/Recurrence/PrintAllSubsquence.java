package com.javatest;

import javax.print.DocFlavor;
import java.util.*;

public class PrintAllSubsquence {
    /**
     * 打印一个字符串的全部子序列，包括空字符串
     *
     */


    public static void printAllSubsquence(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0);
    }

    public static void process(char[] chs, int i) {
        //课程中的代码，不太理解
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process(chs, i + 1);
        chs[i] = tmp;
    }

    public static void function(String str) {
        char[] chs = str.toCharArray();
        process(chs, 0, new ArrayList<Character>());
    }

    public static void process(char[] chs, int i, List<Character> res) {
        if(i == chs.length) {
            printList(res);
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(chs[i]);
        process(chs, i+1, resKeep);
        List<Character> resNoInclude = copyList(res);
        process(chs, i+1, resNoInclude);
    }

    public static void printList(List<Character> res) {
        // ...;
    }

    public static List<Character> copyList(List<Character> list){
        return null;
    }




    public static List<String> ans;
    public static void process_MyMethod(char[] ch, int flag[], int k) {
        //我的方法，使用一个flag数组标记是否要第k个字符
        String subStr;
        if (k == ch.length) {
            subStr = "";
            for (int i = 0;i < flag.length;i++) {
                if (flag[i] == 1) {
                    subStr += ch[i];
                }
            }
            ans.add(subStr);
        } else {
            flag[k] = 0;
            process_MyMethod(ch, flag, k + 1);
            flag[k] = 1;
            process_MyMethod(ch, flag, k + 1);
        }

    }
    public static void main(String[] args) {
        String str = "abc";
        char[] ch = str.toCharArray();
        int[] flag = new int[ch.length];
        ans = new LinkedList<>();
        process_MyMethod(ch, flag, 0);
        System.out.println(ans);
        printAllSubsquence(str);
    }
}
