package com.douma.笔试代码.meituan._20210404;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _5_Candy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] candyA = new int[a];
        for (int i = 0; i < a; i++) {
            candyA[i] = scanner.nextInt();
        }

        int[] candyB = new int[b];
        for (int i = 0; i < b; i++) {
            candyB[i] = scanner.nextInt();
        }

        // 1. 计算 A 的最大的前缀和
        int maxA = 0;
        int[] prefixA = new int[a + 1];
        prefixA[0] = 0;
        for (int i = 1; i <= a; i++) {
            prefixA[i] = prefixA[i - 1] + candyA[i - 1];
            maxA = Math.max(maxA, prefixA[i]);
        }

        // 2. 计算 B 的最大的前缀和
        int maxB = 0;
        int[] prefixB = new int[b + 1];
        prefixB[0] = 0;
        for (int i = 1; i <= b; i++) {
            prefixB[i] = prefixB[i - 1] + candyB[i - 1];
            maxB = Math.max(maxB, prefixB[i]);
        }

        System.out.println(maxA + maxB);
    }
}
