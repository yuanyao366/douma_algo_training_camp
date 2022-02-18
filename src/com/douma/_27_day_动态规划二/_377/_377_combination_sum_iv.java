package com.douma._27_day_动态规划二._377;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _377_combination_sum_iv {
    /* 377. 组合总和 Ⅳ
    给你一个由 不同 整数组成的数组 nums ，
    和一个目标整数 target 。
    请你从 nums 中找出并返回总和为 target 的元素组合的个数。

    题目数据保证答案符合 32 位整数范围。

    示例 1：
    输入：nums = [1,2,3], target = 4
    输出：7
    解释：
    所有可能的组合为：
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)
    请注意，顺序不同的序列被视作不同的组合。

    示例 2：
    输入：nums = [9], target = 3
    输出：0
 

    提示：
    1 <= nums.length <= 200
    1 <= nums[i] <= 1000
    nums 中的所有元素 互不相同
    1 <= target <= 1000

     */

    // 完全背包问题：
    //      在 nums 列表中可重复的选择数字组合，使得组合之和等于 target
    public int combinationSum4(int[] nums, int target) {
        // 1. 状态定义：dp[c] : 能够凑成 target 为 c 的组合数。
        int[] dp = new int[target + 1];

        // 2. 状态初始化
        // 凑成 target 为 0 的组合就是不选择任何整数
        dp[0] = 1;

        // 3. 状态转移
        // 为了不会排除数字相同，但是顺序不同的组合
        // 这里我们针对每一种 target 来选择所有的整数
        for (int c = 1; c <= target; c++) {
            for (int i = 0; i < nums.length; i++) {
                if (c >= nums[i]) dp[c] = dp[c] + dp[c - nums[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(new _377_combination_sum_iv().combinationSum4(nums, 4));
    }
}
