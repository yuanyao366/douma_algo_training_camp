package com.douma._24_day_贪心算法一._322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * @作者 : 老汤
 */
public class _322_CoinChange4 {
    private int minCoins = Integer.MAX_VALUE;
    // 回溯 + 贪心 不能提升性能
    public int coinChange(int[] c, int k) {
        // 升序排列
        Arrays.sort(c);
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
        // 从最大的面值硬币开始 DFS
        for (int i = c.length - 1; i >= 0; i--) {
            if (target - c[i] < 0) continue;
            System.out.println(c[i]);
            coins.add(c[i]);
            dfs(target - c[i], c, coins);
            coins.remove(coins.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] c = {1, 7, 10};
        System.out.println(new _322_CoinChange4().coinChange(c, 14));
    }
}
