package com.douma._30_day_动态规划总结._879;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _879_profitable_schemes {
    /* 879. 盈利计划
    集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

    第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。
    如果成员参与了其中一项工作，就不能参与另一项工作。

    工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

    有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。

    示例 1：
    输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
    输出：2
    解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
    总的来说，有两种计划。

    示例 2：
    输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
    输出：7
    解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
    有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
     
    提示：
    1 <= n <= 100
    0 <= minProfit <= 100
    1 <= group.length <= 100
    1 <= group[i] <= 100
    profit.length == group.length
    0 <= profit[i] <= 100

     */

    /*
    二维费用背包问题
        选择一个工作 i ，需要两个代价：
            1. 人数减掉了 group[i]，人数最多为 n
            2. 利润增加 profit[i]，最少的利润为 minProfit
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;

        // dp[j][k]：选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
        int[][] dp = new int[n + 1][minProfit + 1];
        for (int j = 0; j <= n; j++) {
            dp[j][0] = 1;
        }

        for (int i = 1; i <= group.length; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    // 工作利润至少为 k 而不是工作利润恰好为 k
                    // 所以这个是 max(0, k - earn)，而非 k - earn，下面的代码相当于：
                    /*
                     if(k <= earn)
                        // 相当于当前的工作产生的利润完全满足第二个代价 (即已经满足工作利润至少为 k 的条件了)
                        dp[j][k] = dp[j][k] + dp[j - members][0];
                    else
                        dp[j][k] = dp[j][k] + dp[j - members][k - earn];
                     */
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }

        return dp[n][minProfit];
    }
}
