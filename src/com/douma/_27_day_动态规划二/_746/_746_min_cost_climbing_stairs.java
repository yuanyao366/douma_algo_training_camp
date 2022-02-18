package com.douma._27_day_动态规划二._746;

import java.util.Arrays;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _746_min_cost_climbing_stairs {
    /* 746. 使用最小花费爬楼梯
    数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

    每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，
    你就可以选择向上爬一个阶梯或者爬两个阶梯。

    请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。

     
    示例 1：
    输入：cost = [10, 15, 20]
    输出：15
    解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。

    示例 2：
    输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
    输出：6
    解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
     
    提示：
    cost 的长度范围是 [2, 1000]。
    cost[i] 将会是一个整型数据，范围为 [0, 999] 。
     */

    // 记忆化搜索
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(cost, n, memo);
    }

    private int dfs(int[] cost, int i, int[] memo) {
        if (i == 0 || i == 1) return 0;
        if (memo[i] != -1) return memo[i];
        int left = dfs(cost, i - 1, memo);
        int right = dfs(cost, i - 2, memo);
        memo[i] = Math.min(left + cost[i - 1], right + cost[i - 2]);
        return memo[i];
    }

    // 动态规划
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        // 状态 dp[i]：表示走到第 i 个台阶使用的最小花费
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    // 动态规划 + 状态空间压缩
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i <= n; i++) {
            int tmp = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
            prev = curr;
            curr = tmp;
        }
        return curr;
    }
}
