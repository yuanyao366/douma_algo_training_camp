package com.douma._12_day.practice._643;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _643_maximum_average_subarray_i {
    /* leetcode 643. 子数组最大平均数 I
    给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。

    示例：
    输入：[1,12,-5,-6,50,3], k = 4
    输出：12.75
    解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
     
    提示：
    1 <= k <= n <= 30,000。
    所给数据范围 [-10,000，10,000]。

     */
    // 暴力解法
    // 时间复杂度：O(nk) 超时
    public double findMaxAverage1(int[] nums, int k) {
        double maxAverage = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            double sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }
            maxAverage = Math.max(maxAverage, sum / k);
        }
        return maxAverage;
    }

    // 滑动窗口
    public double findMaxAverage(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int left = 0, right = 0;
        int windowSum = 0;
        while (right < nums.length) {
            windowSum += nums[right];
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[left];
                left++;
            }
            right++;
        }
        return (double)maxSum / k;
    }
}
