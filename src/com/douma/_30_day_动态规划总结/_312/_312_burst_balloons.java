package com.douma._30_day_动态规划总结._312;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _312_burst_balloons {
    /* 312. 戳气球
    有 n 个气球，编号为 0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。

    现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 
    这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。
    如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
    求所能获得硬币的最大数量。

    示例 1：
    输入：nums = [3,1,5,8]
    输出：167
    解释：
    nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
    coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

    示例 2：
    输入：nums = [1,5]
    输出：10
     
    提示：
    n == nums.length
    1 <= n <= 500
    0 <= nums[i] <= 100

     */

    // 动态规划
    // 状态定义：dp[i][j] 表示不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数
    // 状态转移方程：
    //  dp[i][j] = max {dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]} | i < k < j
    //  上面方程中的 nums[i]*nums[k]*nums[j] 表示戳破气球 k 时能得到的金币数
    // 我们可以对上面的方程进行推演下，比如我们要求 dp[i][i + 2]：
    //  dp[i][i + 2] = dp[i][i + 1] + dp[i + 1][i + 2] + nums[i]*nums[i + 1]*nums[i + 2]
    //  其中 dp[i][i + 1] 和 dp[i + 1][i + 2] 都是返回 0，
    //  因为 i 到 i + 1 和 i + 1 到 i + 2 之间都没有气球，
    //  而我们对 dp[i][j] 的定义是不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数
    // 所以说  dp[i][i + 2] = nums[i]*nums[i + 1]*nums[i + 2]，其实就是等于戳破 i + 1 获得的金币
    public int maxCoins(int[] nums) {
        // 加虚拟边界 nums[-1] = nums[nums.length] = 1;
        int[] numsTemp = new int[nums.length + 2];
        for (int i = 1; i <= nums.length; i++) numsTemp[i] = nums[i - 1];
        numsTemp[0] = 1;
        numsTemp[nums.length + 1] = 1;

        // 创建 dp 数组，并且 dp 数组中的每个元素都初始化成 0
        int length = numsTemp.length;
        int[][] dp = new int[length][length];

        // 状态转移方程(计算不戳破 i 与 j ，仅戳破 i 与 j 之间的气球我们能得到的最大金币数)：
        //  dp[i][j] = max {dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]} | i < k < j
        // 这里 i 从右往左遍历的原因：
        // dp[i][j] 依赖于 dp[k][j] ，然而 k 又是大于 i 的，所以需要先计算数组右边的状态
        // 也就是 i 需要从右往左遍历
        for (int i = length - 2; i >= 0; i--) {
            // 这里 +2 的原因：保证 (i, j) 之间肯定 1 个元素，因为没有元素的话，dp[i][j] 就等于 0
            for (int j = i + 2; j < length; j++) {
                int max = 0;
                // 计算戳破 k 气球得到的最大金币数
                for (int k = i + 1; k < j; k++) {
                    // 注意：使用增加了虚拟边界的数组 numsTemp
                    int temp = dp[i][k] + dp[k][j] + numsTemp[i] * numsTemp[k] * numsTemp[j];
                    if (temp > max) max = temp;
                }
                // 记录状态值
                dp[i][j] = max;
            }
        }

        // 返回最终获得的最大硬币数
        // 当然是：计算不戳破 0 与 length - 1 ，仅戳破 0 与 length - 1 之间的气球我们能得到的最大金币数
        // 因为前面我们对原数组 nums 增加了虚拟边界，所以返回 dp[0][length - 1] 就是返回最终获得的最大硬币数
        return dp[0][length - 1];
    }
}
