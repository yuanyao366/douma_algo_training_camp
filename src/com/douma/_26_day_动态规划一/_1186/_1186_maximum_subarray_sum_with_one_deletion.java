package com.douma._26_day_动态规划一._1186;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1186_maximum_subarray_sum_with_one_deletion {
    public int maximumSum(int[] nums) {
        // 状态定义：dp[i][j] 表示以索引为 i 的元素结尾并且剔除 j 个元素的最大子数组和
        int[][] dp = new int[nums.length][2];
        // 状态初始化
        dp[0][0] = nums[0];
        int maxSum = dp[0][0];
        // 状态转移
        for (int i = 1; i < nums.length; i++) {
            // 以索引为 i 的元素结尾，并且剔除 0 个元素的最大子数组和
            // 这个就和 leetcode 53 号题一样了
            dp[i][0] = Math.max(dp[i - 1][0] + nums[i], nums[i]);
            // 以索引为 i 的元素结尾，并且剔除 1 个元素的最大子数组和。这个等于以下值的最大值：
            //      1. 以索引为 i - 1 的元素结尾，并且剔除 nums[i]
            //      2. 以索引为 i - 1 的元素结尾，并且剔除了 1 个元素，不剔除 nums[i]
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + nums[i]);
            maxSum = Math.max(maxSum, Math.max(dp[i][0], dp[i][1]));
        }
        return maxSum;
    }
}
