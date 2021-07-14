package com.douma._26_day._300;

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

        // 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
        int[] dp = new int[nums.length];

        // 状态初始化：单个元素最少有一个递增子序列元素
        Arrays.fill(dp, 1);

        for (int j = 1; j < nums.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                    maxLen = Math.max(maxLen, dp[j]);
                }
            }
        }

        return maxLen;
    }
}
