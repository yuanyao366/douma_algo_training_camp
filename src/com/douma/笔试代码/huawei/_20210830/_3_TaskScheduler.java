package com.douma.笔试代码.huawei._20210830;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */
public class _3_TaskScheduler {

    public static void main(String[] args) {
        // 1. 输入数据处理
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[] taskTimes = new int[n];
        for (int i = 0; i < n; i++) {
            taskTimes[i] = scanner.nextInt();
        }

        // 2. 将所有任务放入到小顶堆
        // 这样可以很快的拿到时间最短的任务
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int task : taskTimes) {
            pq.add(task);
        }

        // 3. 处理器并行处理任务
        // 定义一个 m 长度的数组，模拟 m 个并行的处理器
        // 然后不断将堆顶的任务并行放入到 m 个处理器中
        int[] slots = new int[m];
        while (!pq.isEmpty()) {
            for (int i = 0; i < m; i++) {
                if (!pq.isEmpty()) slots[i] += pq.poll();
                else break;
            }
        }

        // 4. 拿到所有处理器中，耗时最长的处理器，其耗时就是整个耗时
        int maxTime = 0;
        for (int i = 0; i < m; i++) {
            maxTime = Math.max(maxTime, slots[i]);
        }

        System.out.println(maxTime);
    }
}
