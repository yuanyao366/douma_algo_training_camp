package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
/*
    0 - 1 背包：
        有 n 种物品和一个容量为 C 的背包，
        第 i 件物品的重量是 w[i]，价值是 v[i]，件数是 1 件
        求将哪些物品装入背包可使得价值总和最大
 */
public class _1_Knapsack01 {

    private int[] w;
    private int[] v;

    private int maxValue = 0;

    public int knapsack01(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        dfs(-1, C, 0);
        return maxValue;
    }

    private void dfs(int index, int c, int currValue) {
        // 处理当前节点（父节点）
        maxValue = Math.max(maxValue, currValue);

        // 从 index 开始是为了控制顺序
        for (int i = index; i < w.length; i++) {
            // 处理子节点
            int childIndex = i + 1;

            if (childIndex == w.length) continue;
            if (c < w[childIndex]) continue;

            dfs(childIndex, c - w[childIndex], currValue + v[childIndex]);
        }
    }

    public static void main(String[] args) {
        _1_Knapsack01 k = new _1_Knapsack01();
        int w[] = {3, 4, 5};
        int v[] = {15, 10, 12};

        System.out.println(k.knapsack01(w, v, 10));
    }
}
