package com.douma._10_day_栈和队列;

import java.util.*;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class JavaStack {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(2);
        deque.poll();

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(3);
        stack.push(2);
        // 拿到栈顶元素
        System.out.println(stack.peek()); // 2
        // 判断栈是否为空
        System.out.println(stack.isEmpty());
        // 弹出栈顶元素，并返回栈顶元素
        Integer top = stack.pop();
    }
}
