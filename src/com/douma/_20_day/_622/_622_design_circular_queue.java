package com.douma._20_day._622;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _622_design_circular_queue {
}

class MyCircularQueue {
    private int[] data;
    private int head;
    private int tail;

    public MyCircularQueue(int k) {
        data = new int[k + 1];
        head = tail = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        data[tail] = value;
        tail = (tail + 1) % data.length;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        // 这里不需要返回出队的元素
        // head 指针往前走即可
        head = (head + 1) % data.length;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        // tail 指针前一个元素就是队列的最后一个元素
        int index = tail > 0 ? tail - 1 : data.length - 1;
        return data[index % data.length];
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail + 1) % data.length == head;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
