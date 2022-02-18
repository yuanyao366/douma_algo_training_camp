package com.douma._30_day_动态规划总结._1049;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1049_last_stone_weight_ii {
    /* 1049. 最后一块石头的重量 II （1046 的变形题）
        有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。

    每一回合，从中选出任意两块石头，然后将它们一起粉碎。
    假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
        如果 x == y，那么两块石头都会被完全粉碎；
        如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。

    示例 1：
    输入：stones = [2,7,4,1,8,1]
    输出：1
    解释：
    组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
    组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
    组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
    组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。

    示例 2：
    输入：stones = [31,26,33,21,40]
    输出：5

    示例 3：
    输入：stones = [1,2]
    输出：1

    提示：
    1 <= stones.length <= 30
    1 <= stones[i] <= 100

     */

    /*
    记石头的总重量为 sum，被粉碎的重量是 neg，那么没有粉碎的石头重量为 sum - neg
    最后一块石头的重量是：(sum - neg) - neg = sum - 2 * neg

    要使最后一块石头的重量尽可能地小，neg 需要在不超过 sum/2 的前提下尽可能地大

    因此本问题可以看作是背包容量为 sum/2，物品重量和价值均为 stones的 0-1 背包问题。
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int weight : stones) {
            sum += weight;
        }
        // 背包的容量
        int m = sum / 2;

        // dp[c]：表示是否可以将石头放入到容量为 c 的背包中
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;

        for (int i = 0; i < stones.length; i++) {
            for (int c = m; c >= stones[i]; c--) {
                dp[c] = dp[c] || dp[c - stones[i]];
            }
        }

        for (int i = m; ; i--) {
            if (dp[i]) return sum - 2 * i;
        }
    }

}
