package com.douma._26_day_动态规划一._322;

import java.util.ArrayList;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _322_CoinChange1 {
    private int minCoins = Integer.MAX_VALUE;
    // 回溯
    // 时间复杂度是指数级别的，会超时
    public int coinChange(int[] c, int k) {
        // 1. 回溯穷举所有的硬币组合
        dfs(k, c, new ArrayList<>());
        return minCoins == Integer.MAX_VALUE ? -1 : minCoins;
    }

    private void dfs(int target, int[] c,
                     List<Integer> coins) {
        if (target == 0) {
            minCoins = Math.min(minCoins, coins.size());
            return;
        }

        for (int i = 0; i < c.length; i++) {
            if (target - c[i] < 0) continue;
            coins.add(c[i]);
            dfs(target - c[i], c, coins);
            coins.remove(coins.size() - 1);
        }
    }

}
