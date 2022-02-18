package com.douma.笔试代码.huawei._20210831;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 菲菲
 */
public class _1_MaxFav {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(); // 可用的钱总额
        int n = scanner.nextInt(); // 商品的种类
        int[] w = new int[n]; // 每种商品的价格
        int[] p = new int[n]; // 每种商品的数量
        int[] v = new int[n]; // 每种商品的喜爱度
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        // 把多重背包问题，转化成 0-1 背包问题
        // 比如，有 2 件价值为 5，重量为 2 的同一物品，
        // 我们就可以分为物品 a和物品 b，a 和 b 的价值都为 5，重量都为 2，
        // 但我们把它们视作不同的物品。
        int newN = 0;
        for (int i = 0; i < n; i++) {
            newN += p[i];
        }

        int[] newW = new int[newN];
        int[] newV = new int[newN];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p[i]; j++) {
                newW[index] = w[i];
                newV[index] = v[i];
                index++;
            }
        }

        // 1. 状态定义：dp[c] : 将物品放入容量为 c 的背包中产生的最大价值
        int[] dp = new int[x + 1];

        // 2. 状态初始化

        // 3. 状态转移
        for (int i = 0; i < newN; i++) {
            for (int c = x; c >= newW[i]; c--) {
                dp[c] = Math.max(dp[c], newV[i] + dp[c - newW[i]]);
            }
        }

        // 4. 返回结果
        System.out.println(dp[x]);
    }

}
