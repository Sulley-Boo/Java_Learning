package com.javatest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MadianQuick {
    /**
     * 一个数据流中，随时可以取得中位数
     * <p>
     * 将数据流中的数据按照情况加入到一个大根堆和一个小根堆中
     * (1)大根堆为空：加入大根堆
     * (2)当前加入的数小于等于大根堆堆顶元素，将其放入大根堆
     * (3)当前加入的数大于大根堆堆顶元素，将其放入小根堆
     * 每次添加一个元素都要调整两个堆结构的大小，如果两个堆结构大小差2，
     * 则将size较大的堆中堆顶元素放到另一个堆中。
     */

    public static class MedianHolder {
        private PriorityQueue<Integer> minHeap;//小根堆
        private PriorityQueue<Integer> maxHeap;//大根堆

        public MedianHolder() { // 构造函数
            minHeap = new PriorityQueue<>(new minHeapComparator());
            maxHeap = new PriorityQueue<>(new maxHeapComparator());
        }

        private void modifyTwoHeapsSize() { //调整两个堆的大小
            if (this.maxHeap.size() == this.minHeap.size() + 2) {
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }

        public void addNumber(int num) { //向该结构中添加元素
            if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            modifyTwoHeapsSize();
        }

        public Integer getMedian() { //获取中位数
            int minHeapSize = this.minHeap.size();
            int maxHeapSize = this.maxHeap.size();
            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            if (minHeapSize + maxHeapSize == 0) {
                return null;
            }
            if (((minHeapSize + maxHeapSize) & 1) == 0) {
                return (maxHeapHead + minHeapHead) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }
    }

    public static class minHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static class maxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    // for test
    public static int[] getRandomArray(int maxLen, int maxValue) {
        int[] res = new int[(int) (Math.random() * maxLen) + 1];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue);
        }
        return res;
    }

    // for test, this method is ineffective but absolutely right
    public static int getMedianOfArray(int[] arr) {
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        int mid = (newArr.length - 1) / 2;
        if ((newArr.length & 1) == 0) {
            return (newArr[mid] + newArr[mid + 1]) / 2;
        } else {
            return newArr[mid];
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr0 = {1, 2, 3, 5, 6, 7};
        MedianHolder medianHolder = new MedianHolder();
        for (int i = 0; i < arr0.length; i++) {
            medianHolder.addNumber(arr0[i]);
        }
        System.out.println(medianHolder.getMedian());

        boolean err = false;
        int testTimes = 200000;
        for (int i = 0; i != testTimes; i++) {
            int len = 30;
            int maxValue = 1000;
            int[] arr = getRandomArray(len, maxValue);
            MedianHolder medianHold = new MedianHolder();
            for (int j = 0; j != arr.length; j++) {
                medianHold.addNumber(arr[j]);
            }
            if (medianHold.getMedian() != getMedianOfArray(arr)) {
                err = true;
                printArray(arr);
                break;
            }
        }
        System.out.println(err ? "测试失败！" : "测试成功！");
    }
}
