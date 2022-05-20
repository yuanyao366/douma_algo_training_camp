package com.douma._27_day_动态规划二.knapsack;


/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
/*
    完全背包：
        有 n 种物品和一个容量为 C 的背包
        第 i 种物品的重量是 w[i]，价值是 v[i]，件数是无限
        求将哪些物品装入背包可使得价值总和最大
 */
public class _6_KnapsackComplete {

    private int[] w;
    private int[] v;

    private int max = 0;

    public int knapsackComplete(int[] w, int[] v, int C) {
        this.w = w;
        this.v = v;
        return dfs(-1, C);
    }

    // 在容量为 c 的背包里放入第 index 号物品，得到的最大价值
    private int dfs(int index, int c) { // 状态参数
        // 处理当前节点（父节点）
        int maxValue = 0;

        // 从 index 开始是为了控制顺序
        for (int i = index; i < w.length; i++) {
            // 处理子节点
            int childIndex = i;

            if (childIndex == w.length) continue;
            if (childIndex == - 1 || c < w[childIndex]) continue;

            int childMaxValue = dfs(childIndex, c - w[childIndex]);
            maxValue = Math.max(maxValue, childMaxValue);
        }

        return maxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _6_KnapsackComplete k = new _6_KnapsackComplete();
        int w[] = {3, 4, 5};
        int v[] = {15, 10, 12};

        System.out.println(k.knapsackComplete(w, v, 10));
    }

    // 完全背包回溯法也可以这样来实现
    private void dfs1(int price, int c) {
        if (c <= 0) {
            return;
        }
        max = Math.max(max, price);
        for (int i = 0; i < w.length; i++) {
            dfs1(v[i] + price, c - w[i]);
        }
    }
}
