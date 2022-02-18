package com.douma._11_day_优先队列._1046;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1046_last_stone_weight {
    /* leetcode 1046 号算法题：最后一块石头的重量
    有一堆石头，每块石头的重量都是正整数。
    每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。
    假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
        1. 如果 x == y，那么两块石头都会被完全粉碎；
        2. 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

    输入：[2,7,4,1,8,1]
    x = 7, y = 8  --> [2,0,4,1,1,1]
    x = 2, y = 4  --> [0,0,2,1,1,1]
    x = 1, y = 2  --> [0,0,1,0,1,1]
    x = 1, y = 1  --> [0,0,0,0,0,1]
    输出： 1

    1 <= stones.length <= 30
    1 <= stones[i] <= 1000
     */
    // 排序解法
    // O(n^2logn)
    public int lastStoneWeight1(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];
        for (int i = 0; i < n - 1; i++) {
            Arrays.sort(stones);
            int y = stones[n - 1];
            int x = stones[n - 2];
            if (x == 0) break;  // 说明最多只剩一块石头
            stones[n - 1] = y - x;
            stones[n - 2] = 0;
        }
        return stones[n - 1];
    }

    // 大顶堆
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        if (n == 1) return stones[0];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) pq.add(stone);

        while (pq.size() > 1) {
            int y = pq.remove();
            int x = pq.remove();
            int diff = y - x;
            if (diff > 0) pq.add(diff);
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
