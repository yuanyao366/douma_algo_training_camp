package com.douma.笔试代码.ali._20220407;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _2_删除数的最大值 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // 小顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) pq.add(num);

        while (pq.size() > 1) {
            int y = pq.remove();
            int x = pq.remove();
            if (x == y) {
                pq.add(x + y);
            } else {
                pq.add(y);
            }
        }

        System.out.println(pq.peek());
    }

}
