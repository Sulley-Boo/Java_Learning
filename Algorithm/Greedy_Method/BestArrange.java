package com.javatest;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    /**
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体
     * 的项目)，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
     * 返回这个最多的宣讲场次。
     * <p>
     * 贪心策略：项目结束时间最早的优先安排
     */

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    public static int bestArrange(Program[] programs, int start) {
        Arrays.sort(programs, new ProgramComparator());// 对项目按照结束时间递增排序
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if (start <= programs[i].start) {
                result++;
                start = programs[i].end;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Program[] programs1 = new Program[3];
        programs1[0] = new Program(1, 2);
        programs1[1] = new Program(2, 3);
        programs1[2] = new Program(3, 4);
        System.out.println(bestArrange(programs1, 0));

        Program[] programs2 = new Program[3];
        programs2[0] = new Program(1, 3);
        programs2[1] = new Program(2, 4);
        programs2[2] = new Program(3, 5);
        System.out.println(bestArrange(programs2, 0));

        Program[] programs3 = new Program[5];
        programs3[0] = new Program(1, 5);
        programs3[1] = new Program(2, 3);
        programs3[2] = new Program(2, 4);
        programs3[3] = new Program(3, 6);
        programs3[4] = new Program(3, 5);
        System.out.println(bestArrange(programs3, 0));
    }
}
