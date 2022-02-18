package com.douma._12_day_滑动窗口._643;

public class _643_maximum_average_subarray_i {
    /* leetcode 643. 子数组最大平均数 I
    给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

    示例：
    输入：[1,12,-5,-6,50,3], k = 4
    输出：12.75
    解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
    2、51、42

    提示：
    1 <= k <= n <= 30,000。
    所给数据范围 [-10,000，10,000]。

     */

    // 暴力解法
    // 时间复杂度：O(nk) 超时
    public double findMaxAverage1(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = 0;
            // 存在重复计算
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return (double)maxSum / k;
    }

    // 前缀和优化(空间换时间)
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public double findMaxAverage2(int[] nums, int k) {
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            int sum = prefixSum[i + k] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }
        return (double)maxSum / k;
    }

    // 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public double findMaxAverage3(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        // left 是窗口的左边界，right 是窗口的右边界
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            // 满足窗口的条件：达到了固定的窗口大小
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return (double) maxSum / k;
    }
}
