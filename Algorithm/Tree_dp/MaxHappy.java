package com.javatest;

import java.util.*;

public class MaxHappy {
    /**
     * 派对的最大快乐值
     * 员工信息的定义如下:
     * class Employee {
     * public int happy; // 这名员工可以带来的快乐值
     * List<Employee> subordinates; // 这名员工有哪些直接下级
     * }
     * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的
     * 多叉树。树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。 叶节点是没有
     * 任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级。
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来。但是要遵循如下规则。
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你的目标是让派对的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
     */
    public static class Employee {
        public int happy; // 这名员工可以带来的快乐值
        public List<Employee> subordinates; // 这名员工有哪些直接下级

        public Employee(int happy) {
            this.happy = happy;
        }
    }

    public static class Info {
        public int comeMaxHappy;
        public int leaveMaxHappy;

        public Info(int comeMaxHappy, int leaveMaxHappy) {
            this.comeMaxHappy = comeMaxHappy;
            this.leaveMaxHappy = leaveMaxHappy;
        }
    }

    public static Info process(Employee x) {
        if (x.subordinates == null) {
            return new Info(x.happy, 0);
        }
        int come = x.happy;
        int leave = 0;
        for (Employee subordinate : x.subordinates) {
            come += process(subordinate).leaveMaxHappy;
            leave += Math.max(process(subordinate).comeMaxHappy, process(subordinate).leaveMaxHappy);
        }
        return new Info(come, leave);
    }

    public static int getMaxHappy(Employee boss) {
        return Math.max(process(boss).comeMaxHappy, process(boss).leaveMaxHappy);
    }

    public static void main(String[] args) {
        Employee boss1 = new Employee(10);
        Employee subordinate1 = new Employee(80);
        Employee subordinate2 = new Employee(90);
        Employee subordinate3 = new Employee(100);
        List<Employee> subordinate= new ArrayList<>();
        subordinate.add(subordinate1);
        subordinate.add(subordinate2);
        subordinate.add(subordinate3);
        boss1.subordinates = subordinate;
        System.out.println(getMaxHappy(boss1));
        System.out.println("--------");
        Employee boss2 = new Employee(10);
        Employee bosssub1 = new Employee(80);
        Employee bosssub2 = new Employee(90);
        Employee bosssub3 = new Employee(100);
        List<Employee> bosssub= new ArrayList<>();
        bosssub.add(bosssub1);
        bosssub.add(bosssub2);
        bosssub.add(bosssub3);
        boss2.subordinates = bosssub;
        Employee sub1sub1 = new Employee(1000);
        Employee sub2sub1 = new Employee(5);
        List<Employee> sub1sub = new ArrayList<>();
        List<Employee> sub2sub = new ArrayList<>();
        sub1sub.add(sub1sub1);
        sub2sub.add(sub2sub1);
        bosssub1.subordinates = sub1sub;
        bosssub2.subordinates = sub2sub;
        System.out.println(getMaxHappy(boss2));
    }
}
