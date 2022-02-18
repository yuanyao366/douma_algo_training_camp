package com.douma._30_day_动态规划总结._1027;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1027_longest_arithmetic_subsequence {
    /* 1027. 最长等差数列
    给定一个整数数组 A，返回 A 中最长等差子序列的长度。

    回想一下，A 的子序列是列表 A[i_1], A[i_2], ..., A[i_k]
    其中 0 <= i_1 < i_2 < ... < i_k <= A.length - 1。
    并且如果 B[i+1] - B[i]( 0 <= i < B.length - 1) 的值都相同，那么序列 B 是等差的。

    示例 1：
    输入：[3,6,9,12]
    输出：4
    解释：
    整个数组是公差为 3 的等差数列。

    示例 2：
    输入：[9,4,7,2,10]
    输出：3
    解释：
    最长的等差子序列是 [4,7,10]。

    示例 3：
    输入：[20,1,15,3,10,5,8]
    输出：4
    解释：
    最长的等差子序列是 [20,15,10,5]。
     

    提示：
    2 <= A.length <= 2000
    0 <= A[i] <= 10000
     */

    // 经典 LIS 问题
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;

        int ans = 2;
        // dp[i][j]：表示以 nums[i] 为结尾且公差为 j 的最长等差数列的长度
        int[][] dp = new int[n][20010];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                // +10000 的目的：保证公差 diff 为正数
                diff += 10000;
                dp[i][diff] = Math.max(dp[i][diff], dp[j][diff] + 1);
                ans = Math.max(dp[i][diff], ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0,8,45,88,48,68,28,55,17,24};

        System.out.println(new _1027_longest_arithmetic_subsequence().longestArithSeqLength(nums));
    }
}
