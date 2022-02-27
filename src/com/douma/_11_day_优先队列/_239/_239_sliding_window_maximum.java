package com.douma._11_day_优先队列._239;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _239_sliding_window_maximum {
    /* leetcode 239 号算法题：滑动窗口最大值

    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回滑动窗口中的最大值。

    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    输出：[3,3,5,5,6,7]

    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7


    1 <= nums.length <= 10^5
    -10^4 <= nums[i] <= 10^4
    1 <= k <= nums.length

     */

    // 暴力解法
    // O((n-k)*k)
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            int maxNum = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                maxNum = Math.max(maxNum, nums[j]);
            }
            ans[i] = maxNum;
        }
        return ans;
    }

    // 优先队列
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        // tips：这里维护的是大顶堆
        // 两个元素值不想等的话，那么元素大的放在前面，
        // 如果两个元素值相等的话，坐标大的放在前面，这样坐标 小于等于 i - k 的机会就会少点，这样删除的动作就会少发生了，
        // 其实元素相等的时候哪个放在前面，哪个放在后面，都无所谓的
        PriorityQueue<int[]> pq
                = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        for (int i = 0; i < k; i++) {
            pq.add(new int[]{nums[i], i});
        }

        int[] ans = new int[nums.length - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < nums.length; i++) {
            pq.add(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.remove();
            }
            ans[i - k + 1] = pq.peek()[0];
        }

        return ans;
    }

    // 双端队列
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 保证队列里面最多只有 k 个元素
            // 说明：这里的 while 可以使用 if 来代替，因为：
            // 要维护一个大小为 k 的窗口的话，每次最多只需要处理一个元素即可
            if (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            // 如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i < j），并且 i 对应的元素不大于 j 对应的元素（nums[i]≤nums[j]）
            // 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中
            // 由于 nums[j] 的存在，nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i] 永久地移除
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);

            if (i >= k - 1) ans[i - k + 1] = nums[deque.peek()];
        }
        return ans;
    }
}
