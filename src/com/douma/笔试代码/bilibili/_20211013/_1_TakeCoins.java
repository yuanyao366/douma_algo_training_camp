package com.douma.笔试代码.bilibili._20211013;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _1_TakeCoins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] data = scanner.nextLine().split(" ");
        int totalSum = 0, n = data.length;
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(data[i]);
            totalSum += coins[i];
        }
        int k = scanner.nextInt();

        // 通过滑动窗口维护数组中连续的 n - k 个元素的最小和
        int windowMinSum = Integer.MAX_VALUE;
        int windowSum = 0;
        int left = 0, right = 0;
        while (right < n) {
            windowMinSum += coins[right];

            if (right - left + 1 == n - k) {
                windowMinSum = Math.min(windowMinSum, windowSum);
                windowMinSum -= coins[left];
                left++;
            }

            right++;
        }

        // 将总和减去中间 n - k 个元素的最小和，得到的就是从两端可以拿到的最大和
        System.out.println(totalSum - windowMinSum);
    }
}
