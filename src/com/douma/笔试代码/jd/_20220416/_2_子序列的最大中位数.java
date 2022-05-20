package com.douma.笔试代码.jd._20220416;

import java.util.*;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 小莹
 */

// 3. 大顶堆 + 小顶堆求数据流中的中位数
// 见 leetcode 295 题
class MedianFinder {
    // 大顶堆用于存储较小的一半元素
    private PriorityQueue<Integer> maxHeap;
    // 小顶堆用于存储较大的一半元素
    private PriorityQueue<Integer> minHeap;

    // 注意：如果元素的个数是奇数的话，那么大顶堆中的元素个数比小顶堆中元素个数多 1

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    // 从数据流中添加一个整数到数据结构中。
    // 时间复杂度：log(n)
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        }
    }

    // 返回目前所有元素的中位数。
    // 时间复杂度：O(1)
    public int findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (!maxHeap.isEmpty()){
            return maxHeap.peek();
        } else {
            return 0;
        }
    }
}

public class _2_子序列的最大中位数 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        while (T > 0) {
            int n = scanner.nextInt();
            // 如果只有一个数的话，那么它就是最大的中位数
            if (n == 1) {
                System.out.println(scanner.nextInt());
                T--;
                continue;
            }

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            List<Integer> selectedNums = new ArrayList<>();
            int selectedIndex = -1;
            for (int i = 0; i < n; i++) {
                selectedIndex = i;
                if (i + 1 < n && nums[i + 1] > nums[i]) {
                    selectedIndex = i + 1;
                    i++;
                }
                selectedNums.add(nums[selectedIndex]);
            }

            Collections.sort(selectedNums);
            System.out.println(selectedNums.get((selectedNums.size() - 1) / 2));

            T--;
        }
    }
}
