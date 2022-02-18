package com.douma._20_day_数据结构设计._225;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _225_implement_stack_using_queues {
    /* 225. 用队列实现栈
        请你仅使用两个队列实现一个后入先出（LIFO）的栈，
        并支持普通栈的全部四种操作（push、top、pop 和 empty）。

        实现 MyStack 类：
            void push(int x) 将元素 x 压入栈顶。
            int pop() 移除并返回栈顶元素。
            int top() 返回栈顶元素。
            boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。

        输入：
        ["MyStack", "push", "push", "top", "pop", "empty"]
        [[], [1], [2], [], [], []]
        输出：
        [null, null, null, 2, 2, false]

        解释：
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top(); // 返回 2
        myStack.pop(); // 返回 2
        myStack.empty(); // 返回 False

        提示：
            1 <= x <= 9
            最多调用 100 次 push、pop、top 和 empty
            每次调用 pop 和 top 都保证栈不为空

        注意：
        1. 你只能使用队列的基本操作 ——
            也就是 push to back、peek/pop from front、size 和 is empty 这些操作。
        2. 你所使用的语言也许不支持队列。 
        你可以使用 list （列表）或者 deque（双端队列）来模拟一个队列 ,
        只要是标准的队列操作即可。


     */
}


class MyStack1 {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack1() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    // 时间复杂度：O(1)
    public void push(int x) {
        queue1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    // 时间复杂度：O(n)
    public int pop() {
        if (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                Integer x = queue1.poll();
                if (queue1.isEmpty()) return x;
                queue2.offer(x);
            }
        } else if (!queue2.isEmpty()){
            while (!queue2.isEmpty()) {
                Integer x = queue2.poll();
                if (queue2.isEmpty()) return x;
                queue1.offer(x);
            }
        }
        throw new RuntimeException("没有数据");
    }

    /** Get the top element. */
    // 时间复杂度：O(n)
    public int top() {
        if (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                Integer x = queue1.poll();
                queue2.offer(x);
                if (queue1.isEmpty()) return x;
            }
        } else if (!queue2.isEmpty()){
            while (!queue2.isEmpty()) {
                Integer x = queue2.poll();
                queue1.offer(x);
                if (queue2.isEmpty()) return x;
            }
        }
        throw new RuntimeException("没有数据");
    }

    /** Returns whether the stack is empty. */
    // 时间复杂度：O(1)
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

class MyStack2 {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue1 = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    // 时间复杂度：O(n)
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /** Removes the element on top of the stack and returns that element. */
    // 时间复杂度：O(1)
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    // 时间复杂度：O(1)
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    // 时间复杂度：O(1)
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

class MyStack {
    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    // 时间复杂度：O(n)
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    // 时间复杂度：O(1)
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    // 时间复杂度：O(1)
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    // 时间复杂度：O(1)
    public boolean empty() {
        return queue.isEmpty();
    }
}
