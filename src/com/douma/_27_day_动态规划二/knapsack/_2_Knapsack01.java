package com.douma._27_day_动态规划二.knapsack;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _2_Knapsack01 {

    private int[] w;
    private int[] v;

    public int knapsack01(int[] w, int[] v, int C) {
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
            int childIndex = i + 1;

            if (childIndex == w.length) continue;
            if (c < w[childIndex]) continue;

            int childMaxValue = dfs(childIndex, c - w[childIndex]);
            maxValue = Math.max(maxValue, childMaxValue);
        }

        return maxValue + (index == -1 ? 0 : v[index]);
    }

    public static void main(String[] args) {
        _2_Knapsack01 k = new _2_Knapsack01();
        int w[] = {3, 4, 5};
        int v[] = {15, 10, 12};

        System.out.println(k.knapsack01(w, v, 10));
    }
}
