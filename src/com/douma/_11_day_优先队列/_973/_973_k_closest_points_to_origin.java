package com.douma._11_day_优先队列._973;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _973_k_closest_points_to_origin {
    /* leetcode 973 号算法题：最接近原点的 K 个点
        我们有一个由平面上的点组成的列表 points。
        需要从中找出 K 个距离原点 (0, 0) 最近的点。
        （这里，平面上两点之间的距离是欧几里德距离。）
        你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。

        输入：points = [[3,3],[5,-1],[-2,4]], K = 2
            3*3 + 3*3 = 18
            5*5 + -1*(-1) = 26
            -2*(-2) + 4 * 4 = 20
        输出：[[-2,4], [3,3]]

        1 <= K <= points.length <= 10000
        -10000 < points[i][0] < 10000
        -10000 < points[i][1] < 10000
     */

    // 按照欧几里得距离排序
    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                return  (point1[0] * point1[0] + point1[1] * point1[1])
                        - (point2[0] * point2[0] + point2[1] * point2[1]);
            }
        });

        return Arrays.copyOfRange(points, 0, K);
    }

    // 大顶堆
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq
                = new PriorityQueue<>(K + 1, (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));

        for (int[] point : points) {
            pq.add(point);
            if (pq.size() > K) pq.remove();
        }

        // 小顶堆中存储的就是出现了前 k 个高频的元素
        int[][] res = new int[K][2];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.remove();
        }
        return res;
    }


    private Random random = new Random(System.currentTimeMillis());
    // 前 K 个最大元素 --> 升序 --> 拿到后面 K 个元素即可
    // 前 K 个最小元素 --> 降序 --> 拿到后面 K 个元素即可
    public int[][] kClosest3(int[][] points, int K) {
        int n = points.length;
        int target = n - K;

        int left = 0, right = n - 1;
        while (true) {
            int index = partition(points, left, right);
            if (index == target) {
                break;
            } else if (index < target) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        return Arrays.copyOfRange(points, n - K, n);
    }

    private int partition(int[][] points, int left, int right) {
        if (right > left) {
            int pivotIndex = left + 1 + random.nextInt(right - left);
            swap(points, pivotIndex, right);
        }
        int pivot = points[right][0] * points[right][0]
                + points[right][1] * points[right][1];
        int less = left, great = left;
        for (; great < right; great++) {
            int num = points[great][0] * points[great][0]
                    + points[great][1] * points[great][1];
            // 降序排列
            if (num >= pivot) {
                swap(points, less, great);
                less++;
            }
        }
        swap(points, less, right);
        return less;
    }

    private void swap(int[][] nums, int i, int j) {
        int[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
