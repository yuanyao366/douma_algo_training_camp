package com.douma._30_day_动态规划总结._410;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _410_split_array_largest_sum {
    /* 410. 分割数组的最大值
    给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。

    设计一个算法使得这 m 个子数组各自和的最大值最小。

    示例 1：
    输入：nums = [7,2,5,10,8], m = 2
    输出：18
    解释：
    一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
    因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
    示例 2：
    输入：nums = [1,2,3,4,5], m = 2
    输出：9

    示例 3：
    输入：nums = [1,4,4], m = 3
    输出：4
     
    提示：
    1 <= nums.length <= 1000
    0 <= nums[i] <= 106
    1 <= m <= min(50, nums.length)
     */

    // 和 leetcode 813 类似
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] += prefixSum[i - 1] + nums[i - 1];
        }

        // dp[i][j]: 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                for (int l = 0; l < i; l++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[l][j - 1], prefixSum[i] - prefixSum[l]));
                }
            }
        }

        return dp[n][m];
    }
}
