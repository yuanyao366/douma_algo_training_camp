package com.douma.笔试代码.huawei._20210831;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 菲菲
 */
public class _2_TakeOut {
    private static int res = 0;
    private static int[] prices;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int m = scanner.nextInt();
        prices = new int[m];
        for (int i = 0; i < m; i++) {
            prices[i] = scanner.nextInt();
        }

        dfs(0, x);

        System.out.println(res);
    }

    private static void dfs(int index, int target) {
        if (target == 0) {
            res++;
            return;
        }

        for (int i = index; i < prices.length; i++) {
            if (target < prices[i]) continue;
            dfs(i + 1, target - prices[i]);
        }
    }
}
