package com.douma._11_day_优先队列;

import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class JavaHeap {
    public static void main(String[] args) {
        // 默认是小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 大顶堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> b - a);

        // 往堆中添加元素
        pq.add(3);
        pq.offer(4);

        // 删除堆顶元素
        Integer top = pq.remove();
        pq.poll();

        // 拿到堆顶元素
        Integer e = pq.peek();
    }
}
