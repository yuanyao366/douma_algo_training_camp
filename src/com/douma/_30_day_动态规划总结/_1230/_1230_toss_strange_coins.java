package com.douma._30_day_动态规划总结._1230;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1230_toss_strange_coins {
    /* 1230. 抛掷硬币
    有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。

    请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。

    示例 1：
    输入：prob = [0.4], target = 1
    输出：0.40000

    示例 2：
    输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
    输出：0.03125
     
    提示：
    1 <= prob.length <= 1000
    0 <= prob[i] <= 1
    0 <= target <= prob.length
    如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。

     */
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;

        // dp[i][j] ：表示前 i 枚硬币抛掷正面 j 次的概率
        double[][] dp = new double[n + 1][target + 1];

        dp[0][0] = 1;
        for(int i = 1; i <= n; i++){
            // 当前投掷中有 0 次为正面，一种可能是上一次投掷就已经是 0 次正面了，本次投掷结果是反面
            dp[i][0] = dp[i - 1][0] * (1 - prob[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                // 当前投掷中有 j 次为正面，一种可能是上一次投掷就已经是 j 次正面了，本次投掷结果是反面
                dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1]);

                // 前一次有 j - 1 次为正面，本次投掷为正面的可能性
                dp[i][j] += dp[i - 1][j - 1] * prob[i - 1];
            }
        }

        return dp[n][target];
    }
}
