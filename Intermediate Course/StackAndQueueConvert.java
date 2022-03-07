package com.javatest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueueConvert {
    /**
     * 如何仅用队列结构实现栈结构?
     * 如何仅用栈结构实现队列结构?
     */
    public static class TwoStacksQueue {
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public void push(int pushInt) {
            stackPush.push(pushInt);
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }

    //方法1：使用两个队列
    public static class TwoQueuesStack1 {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public TwoQueuesStack1() {
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        public void push(int pushInt) {
            queue.add(pushInt);
        }

        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            help.add(res);
            swap();
            return res;
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            swap();
            return res;
        }

        private void swap() {
            Queue<Integer> tmp = help;
            help = queue;
            queue = tmp;
        }

    }

    //方法2：使用一个队列
    public static class TwoQueuesStack2 {
        private Queue<Integer> queue;

        public TwoQueuesStack2() {
            this.queue = new LinkedList<>();
        }

        public void push(int pushInt) {
            int size = queue.size();
            queue.offer(pushInt);
            while (size > 0) {
                queue.offer(queue.poll());
                size--;
            }
        }

        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return queue.peek();
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            return queue.poll();
        }
    }

    public static void main(String[] args) {
        TwoQueuesStack1 myStack1 = new TwoQueuesStack1();
        myStack1.push(1);
        myStack1.push(2);
        System.out.println(myStack1.peek());
        myStack1.push(3);
        System.out.println(myStack1.pop());
        System.out.println(myStack1.peek());
        System.out.println("========");
        TwoQueuesStack2 myStack2 = new TwoQueuesStack2();
        myStack2.push(1);
        myStack2.push(2);
        System.out.println(myStack2.peek());
        myStack2.push(3);
        System.out.println(myStack2.pop());
        System.out.println(myStack2.peek());
    }
}
