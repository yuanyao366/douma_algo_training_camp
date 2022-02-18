package com.douma._20_day_数据结构设计._155;

import java.util.Stack;

/**
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _155_MinStack {
}

// 1. 单栈实现
class MinStack1 {
    private Stack<Integer> stack;

    public MinStack1() {
        stack = new Stack<>();
    }

    // 时间复杂度：O(1)
    public void push(int x) {
        stack.push(x);
    }
    // 时间复杂度：O(1)
    public void pop() {
        stack.pop();
    }
    // 时间复杂度：O(1)
    public int top() {
        return stack.peek();
    }
    // 时间复杂度：O(n)
    public int getMin() {
        int minValue = stack.peek();
        for (Integer i : stack) {
            minValue = Math.min(minValue, i);
        }
        return minValue;
    }
}

// 2. 双栈实现
class MinStack2 {
    // 存放数据的栈
    private Stack<Integer> dataStack;
    // 存放最小数的栈
    private Stack<Integer> minStack;

    public MinStack2() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }
    // 时间复杂度：O(1)
    public void push(int x) {
        dataStack.push(x);
        // bug 修复：视频中少了 = ，= 号是需要加上的
        // 如果去掉等于的话，可能会出现 dataStack 不为空，但是 minStack 为空了
        // 这样下面的 getMin 就会出现异常了
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }
    // 时间复杂度：O(1)
    public void pop() {
        int top = dataStack.pop();
        if (top == minStack.peek()) {
            minStack.pop();
        }
    }
    // 时间复杂度：O(1)
    public int top() {
        return dataStack.peek();
    }
    // 时间复杂度：O(1)
    public int getMin() {
        return minStack.peek();
    }
}

// 3. 单栈 + 每个元素记住当前为止的最小值
class MinStack3 {
    private Stack<Node> stack;

    public MinStack3() {
        stack = new Stack<>();
    }

    // 时间复杂度：O(1)
    public void push(int x) {
        Node node = new Node();
        node.val = x;
        int min = x;
        if (!stack.isEmpty() && stack.peek().min < x) {
            min = stack.peek().min;
        }
        node.min = min;
        stack.push(node);
    }
    // 时间复杂度：O(1)
    public void pop() {
        stack.pop();
    }
    // 时间复杂度：O(1)
    public int top() {
        return stack.peek().val;
    }
    // 时间复杂度：O(1)
    public int getMin() {
        return stack.peek().min;
    }
}

// 4. 单链表实现(表头作为栈顶)
class MinStack4 {
    private Node dummyHead;

    public MinStack4() {
        dummyHead = new Node();
    }

    // 时间复杂度：O(1)
    public void push(int x) {
        int min = x;
        Node head = dummyHead.next;
        if (head != null && head.min < x) {
            min = head.min;
        }
        Node node = new Node(x, min);
        node.next = dummyHead.next;
        dummyHead.next = node;
    }
    // 时间复杂度：O(1)
    public void pop() {
        Node head = dummyHead.next;
        if (head != null) {
            dummyHead.next = head.next;
            head.next = null;
        }
    }
    // 时间复杂度：O(1)
    public int top() {
        return dummyHead.next.val;
    }
    // 时间复杂度：O(1)
    public int getMin() {
        return dummyHead.next.min;
    }
}

class Node {
    int val;
    int min;
    // 这个属性在方法 4 中使用
    Node next;

    public Node() {

    }
    public Node(int val, int min) {
        this.val = val;
        this.min = min;
    }
}