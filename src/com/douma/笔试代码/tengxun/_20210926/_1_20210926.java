package com.douma.笔试代码.tengxun._20210926;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
// issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4C16V
public class _1_20210926 {
    // 暴力解法
    // 时间复杂度：O(n^2 * logn)
    // 空间复杂度：O(1)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 第一行是节点的个数
        int n = Integer.parseInt(scanner.nextLine());

        // 第二行是每个节点的值
        String[] datum = scanner.nextLine().split(" ");
        int[] data = new int[n + 1];
        for (int i = 0; i < n; i++) {
            data[i + 1] = Integer.parseInt(datum[i]);
        }

        // 第三行是每个节点的父亲节点的索引
        datum = scanner.nextLine().split(" ");
        int[] parents = new int[n + 1];
        parents[1] = 1;
        for (int i = 0; i < n - 1; i++) {
            parents[i + 2] = Integer.parseInt(datum[i]);
        }

        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (isAncestor(i, j, parents) && isSquareNumber(data[i] * data[j])) {
                    res++;
                }
            }
        }

        System.out.println(res);
    }

    // 根据 parents 数组来判断 i 节点是不是 j 节点的祖先
    // 时间复杂度是：O(logn)，因为树的高度平均为 logn，n 的大小最大为 10^5
    private static boolean isAncestor(int i, int j, int[] parents) {
        while (j != 1) {
            if (j == i) {
                return true;
            }
            j = parents[j];
        }
        return false;
    }

    // 详见 leetcode 367. 有效的完全平方数：https://leetcode-cn.com/problems/valid-perfect-square/
    // 时间复杂度是：O(logn)，因为这道题目的节点的值最大为 100，所以这里的 num 最大为 100 * 100 = 10000
    // log10000 约等于 13 ，这个性能是可以接受的
    private static boolean isSquareNumber(int num) {
        if (num < 2) {
            return true;
        }
        int left = 0, right = num / 2;
        while (left <= right) {
            int x = left + (right - left) / 2;
            int guessSquared = x * x;
            if (guessSquared == num) {
                return true;
            } else if (guessSquared > num) {
                right = x - 1;
            } else {
                left = x + 1;
            }
        }
        return false;
    }
}
