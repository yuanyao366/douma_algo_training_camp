package com.douma._27_day_动态规划二._416;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _416_partition_equal_subset_sum {
    /* 416. 分割等和子集
    给你一个 只包含正整数 的 非空 数组 nums 。
    请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

    示例 1：
    输入：nums = [1,5,11,5]
    输出：true
    解释：数组可以分割成 [1, 5, 5] 和 [11] 。

    示例 2：
    输入：nums = [1,2,3,5]
    输出：false
    解释：数组不能分割成两个元素和相等的子集。
     
    提示：
    1 <= nums.length <= 200
    1 <= nums[i] <= 100

     */

    // 先计算得到数组的总和为 sum，然后将 sum / 2 得到一半，记为 target(背包容量)
    // 问题转变为 0-1 背包问题：
    // 在数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合呢？
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        // dp[c] : 表示从 nums 中是否可以找到总和等于 c 的元素组合
        /*
        说明：这里不就是数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合呢？
            我们可以不可以和前面讲过的目标和题目一样，这样定义状态呢：
                dp[c] : 表示从 nums 中找到总和等于 c 的元素组合数
            状态转移方程为：dp[c] = dp[c] + dp[c - nums[i]];
            最后的话，我们只要判断 dp[target] 是否大于 0 即可
        注意：这种方案是不行的，详情请参考 issue：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4TGJ2
         */
        boolean[] dp = new boolean[target + 1];

        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            // bug 修复：这里 c 必须大于等于 nums[i]
            for (int c = target; c >= nums[i]; c--) {
                dp[c] = dp[c] || dp[c - nums[i]];
            }
        }

        return dp[target];
    }
}
