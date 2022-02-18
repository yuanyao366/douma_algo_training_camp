package com.douma._27_day_动态规划二._518;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _518_coin_change_2 {
    /* 518. 零钱兑换 II
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
    假设每一种面额的硬币有无限个。 
    题目数据保证结果符合 32 位带符号整数。

     
    示例 1：
    输入：amount = 5, coins = [1, 2, 5]
    输出：4
    解释：有四种方式可以凑成总金额：
    5=5
    5=2+2+1
    5=2+1+1+1
    5=1+1+1+1+1

    示例 2：
    输入：amount = 3, coins = [2]
    输出：0
    解释：只用面额 2 的硬币不能凑成总金额 3

     */

    // 转化为完全背包问题：
    //     从 coins 列表中可重复选择硬币组合，使得他们的总金额为 amount
    //     请问有多少种组合
    public int change(int amount, int[] coins) {
        // 1. 状态定义：dp[c] : 硬币列表能够凑成总金额为 c 的组合数。
        int[] dp = new int[amount + 1];

        // 2. 状态初始化
        // 凑成总金额为 0 的组合就是不选择任何硬币
        dp[0] = 1;

        // 3. 状态转移
        for (int i = 0; i < coins.length; i++) {
            for (int c = coins[i]; c <= amount; c++) {
                dp[c] = dp[c] + dp[c - coins[i]];
            }
        }

        return dp[amount];
    }
}
