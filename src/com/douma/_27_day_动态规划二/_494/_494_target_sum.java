package com.douma._27_day_动态规划二._494;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _494_target_sum {
    /* 494. 目标和
    给你一个整数数组 nums 和一个整数 target 。

    向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

    例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，
    然后串联起来得到表达式 "+2-1 = 1" 。
    返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。


    示例 1：
    输入：nums = [1,1,1,1,1], target = 3
    输出：5
    解释：一共有 5 种方法让最终目标和为 3 。
    -1 + 1 + 1 + 1 + 1 = 3
    +1 - 1 + 1 + 1 + 1 = 3
    +1 + 1 - 1 + 1 + 1 = 3
    +1 + 1 + 1 - 1 + 1 = 3
    +1 + 1 + 1 + 1 - 1 = 3

     */

    // 假设数组中所有数字的总和为 sum
    // 假设前面设置为负数的数字的总和是 neg，那么设置为正数的数字的总和为 sum - neg
    // (sum - neg) - neg = target => neg = (sum - target) / 2
    // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg(背包容量)
    // 求有多少组合数？
    // 0 - 1 背包问题，一维状态数组实现，下面有二维状态数组实现
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;

        // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
        // 求有多少组合数？
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int c = neg; c >= nums[i]; c--) {
                dp[c] = dp[c] + dp[c - nums[i]];
            }
        }

        return dp[neg];
    }

    // 0 - 1 背包问题，二维状态数组实现
    // 物品：数组中的元素
    // 背包容量：neg
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;

        int diff = sum - target;
        if (diff < 0 || diff % 2 == 1) return 0;

        int neg = diff / 2;

        // 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
        // 求有多少组合数？

        // 1. 状态定义：dp[i][c] 表示选择前 i 个数字组合，组合中所有数字之和为 c 的组合数
        // i 表示前 i 个数字，注意是个，所以 i 的取值范围为 [0...nums.length + 1]
        int[][] dp = new int[nums.length + 1][neg + 1];

        for (int c = 0; c <= neg; c++) {
            // 考虑不选择任何数字
            dp[0][c] = c == 0 ? 1 : 0;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int c = 0; c <= neg; c++) {
                int num = nums[i - 1];
                // 如果容量小于数字，那么表示不能选 num
                if (c < num) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 这里 c >= num，有两种情况：
                    // ① 不选 num ，那么方案数为：dp[i - 1][c]
                    // ② 选 num，那么方案数为 dp[i - 1][c - num]
                    // 总方案数为 ① + ②
                    dp[i][c] = dp[i - 1][c] + dp[i - 1][c - num];
                }
            }
        }

        return dp[nums.length][neg];
    }

    // DFS 解法
    private int ans = 0;
    public int findTargetSumWays2(int[] nums, int S) {
        dfs(nums, S, 0, 0);
        return ans;
    }

    private void dfs(int[] nums, int S, int i, int sum) {
        if (i == nums.length) {
            if (sum == S) ans++;
            return;
        }

        dfs(nums, S, i + 1, sum + nums[i]);
        dfs(nums, S, i + 1, sum - nums[i]);
    }
}
