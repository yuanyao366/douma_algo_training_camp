package com.douma._30_day_动态规划总结._918;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _918_maximum_sum_circular_subarray {
    /* 918. 环形子数组的最大和
    给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。

    在此处，环形数组意味着数组的末端将会与开头相连呈环状。
    （形式上，当0 <= i < A.length 时 C[i] = A[i]，
    且当 i >= 0 时 C[i+A.length] = C[i]）

    此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。
    （形式上，对于子数组 C[i], C[i+1], ..., C[j]，
    不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）

    示例 1：
    输入：[1,-2,3,-2]
    输出：3
    解释：从子数组 [3] 得到最大和 3

    示例 2：
    输入：[5,-3,5]
    输出：10
    解释：从子数组 [5,5] 得到最大和 5 + 5 = 10

    示例 3：
    输入：[3,-1,2,-1]
    输出：4
    解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4

    示例 4：
    输入：[3,-2,2,-3]
    输出：3
    解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3

    示例 5：
    输入：[-2,-3,-1]
    输出：-1
    解释：从子数组 [-1] 得到最大和 -1
     

    提示：
    -30000 <= A[i] <= 30000
    1 <= A.length <= 30000

     */

    /*
        数组是环形数组，意味着可以数组的首尾相连，头部和尾巴的元素是连续的
        那么，要求最大子数组和，包括了两种情况：
        1. 假设数组不是环形数组，这个时候只需要按照 leetcode 53 那样在数组内求最大子数组和
        2. 假设数组是环形数组，而且最大子数组和的子数组包含了首尾两个元素
            这种情况，我们只需要求出数组内最小子数组和，然后使用数组累加和减去最小子数组和即可
        最后，返回上面两种情况的最大值

        举个例子：
        输入数组：3 -2 4 -1

            第一种情况：首尾不相邻的时候的最大连续子数组和：res1 = 3 - 2 + 4 = 5

            第二种情况：
             先计算出首尾不相邻的时候的最小连续子数组和：minSum = -2

             再计算整个数组的累加和等于：totalSum = 3 - 2 + 4 - 1 = 4

             最后首尾相邻的时候的最大连续子数组和等于 : res2 =  totalSum - minSum = 6

            最终结果为：max(res1, res2)
     */
    public int maxSubarraySumCircular(int[] nums) {
        // 1. 在原数组 nums 内求最大子数组和，参考 leetcode 53 号算法题：最大子序和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }

        // 如果最子数组和小于 0，说明数组中全为负数，返回最大负数即可
        if (maxSum < 0) return maxSum;

        // 2. 在原数组 nums 内求最小子数组和
        dp = new int[nums.length];
        dp[0] = nums[0];
        int minSum = dp[0];
        int sum = nums[0]; // 存储 nums 所有元素和
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.min(dp[i - 1] + nums[i], nums[i]);
            minSum = Math.min(minSum, dp[i]);
            sum += nums[i];
        }

        return Math.max(maxSum, sum - minSum);
    }
}
