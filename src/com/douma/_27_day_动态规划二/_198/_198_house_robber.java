package com.douma._27_day_动态规划二._198;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _198_house_robber {
    /* 198. 打家劫舍
    你是一个专业的小偷，计划偷窃沿街的房屋。
    每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，
    一夜之内能够偷窃到的最高金额。

    示例 1：
    输入：[1,2,3,1]
    输出：4
    解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
         偷窃到的最高金额 = 1 + 3 = 4 。

    示例 2：
    输入：[2,7,9,3,1]
    输出：12
    解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
         偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 400

     */

    // 记忆化搜索(一)
    public int rob1(int[] nums) {
        // memo[i]：存储偷盗 [i, nums.length - 1] 区间房子得到的最大金额
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs(nums, 0, memo);
    }

    private int dfs(int[] nums, int i, int[] memo) {
        if (i >= nums.length) return 0;

        if (memo[i] != -1) return memo[i];

        int left = dfs(nums, i + 1, memo);
        int right = dfs(nums, i + 2, memo);

        memo[i] = Math.max(left, right + nums[i]);
        return memo[i];
    }

    // 记忆化搜索(二)
    public int rob2(int[] nums) {
        // memo[i]：存储偷盗 [0, i] 区间房子得到的最大金额
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dfs2(nums, nums.length - 1, memo);
    }

    private int dfs2(int[] nums, int i, int[] memo) {
        if (i == 0) return nums[0];
        if (i == 1) return Math.max(nums[0], nums[1]);

        if (memo[i] != -1) return memo[i];

        int left = dfs2(nums, i - 1, memo);
        int right = dfs2(nums, i - 2, memo);

        memo[i] = Math.max(left, right + nums[i]);
        return memo[i];
    }

    // 动态规划
    public int rob3(int[] nums) {
        if (nums.length == 1) return nums[0];

        // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    // 动态规划 + 状态压缩
    public int rob4(int[] nums) {
        if (nums.length == 1) return nums[0];

        int prevMax = 0;
        int currMax = 0;

        for (int num : nums) {
            int tmpMax = Math.max(currMax, prevMax + num);
            prevMax = currMax;
            currMax = tmpMax;
        }

        return currMax;
    }
}
