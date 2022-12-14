package com.douma._26_day_动态规划一._486;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _486_PredictTheWinner3 {
    public boolean PredictTheWinner(int[] nums) {
        // dp[i][j] 表示玩家 1 在区间 [i, j] 之内可以赢的最多的分
        int[][] dp = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
            dp[i][i] = nums[i];
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < nums.length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                        nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][nums.length - 1] >= 0;
    }


}
