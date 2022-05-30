package com.douma._27_day_动态规划二._213;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _213_house_robber_ii {
    /* 213. 打家劫舍 II
    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
    这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
    同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，
    系统会自动报警 。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，
    今晚能够偷窃到的最高金额。


    示例 1：
    输入：nums = [2,3,2]
    输出：3
    解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。

    示例 2：
    输入：nums = [1,2,3,1]
    输出：4
    解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
            偷窃到的最高金额 = 1 + 3 = 4 。

    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 1000
     */

    /*
        [2,7,9,3,1] = max([7,9,3,1], [2,7,9,3])
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int notRobLastHouse = rob(nums, 0, n - 2);
        int notRobFirstHouse = rob(nums, 1, n - 1);
        return Math.max(notRobFirstHouse, notRobLastHouse);
    }

    // 动态规划 + 状态压缩
    public int rob(int[] nums, int start, int end) {
        if (nums.length == 1) return nums[0];

        int prevMax = 0;
        int currMax = 0;

        for (int i = start; i <= end; i++) {
            int tmpMax = Math.max(currMax, prevMax + nums[i]);
            prevMax = currMax;
            currMax = tmpMax;
        }

        return currMax;
    }

    // 状态没压缩的代码
    public int rob2(int[] nums, int start, int end) {
        if (end - start + 1 == 1) return nums[start];

        // dp[i]：表示偷盗 [0, i] 区间房子得到的最大金额
        int[] dp = new int[nums.length];

        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[end];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int res = new _213_house_robber_ii().rob(nums);
        System.out.println(res);
    }


}
