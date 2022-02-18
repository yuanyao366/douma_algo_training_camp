package com.douma._26_day_动态规划一._300;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _300_LengthOfLongestIncrementSubSeq {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxLen = 1;

        // 状态：dp[i] 表示以 nums[i] 结尾时最长递增子序列的长度
        int[] dp = new int[nums.length];

        // 状态初始化：单个元素最少有一个递增子序列元素
        Arrays.fill(dp, 1);

        // 代码优化：将 i 和 j 换了一个位置
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }

        return maxLen;
    }
}
