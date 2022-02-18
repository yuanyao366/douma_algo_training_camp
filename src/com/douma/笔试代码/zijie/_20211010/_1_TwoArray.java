package com.douma.笔试代码.zijie._20211010;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 菲菲
 */
public class _1_TwoArray {
    // 时间复杂度：O((m + n)logn)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();

            int[] a = new int[n];
            int[] b = new int[m];
            for (int j = 0; j < n; j++) {
                a[j] = scanner.nextInt();
            }
            for (int j = 0; j < m; j++) {
                b[j] = scanner.nextInt();
            }

            // 时间复杂度：O(nlogn)
            Arrays.sort(a);

            long ans = Long.MAX_VALUE;
            int left = 0, right = n - 1;
            // 对 a 应用二分
            // 时间复杂度：O(mlogn)
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long minSquare = Long.MAX_VALUE;
                //  b 的话线性扫描
                for (int j = 0; j < m; j++) {
                    long square = (a[mid] - b[j]) * (a[mid] - b[j]);
                    long tmp = Math.abs(square - k * k);
                    if (tmp < ans) {
                        minSquare = square;
                        ans = tmp;
                    }
                }
                if (minSquare == k * k) break;
                // 使用满足 abs(square - k * k) 最小的 square 作为二分的条件
                // 如果 min_square 比 k^2 要小的话，那应该去搜索 a 的右半区
                // 因为左半区的元素更小，那么 min_square 就会更小，那么 abs(min_square - k^2) 只会更大
                // 去右半区的话，那么 min_square 就有可能会变大，那么 abs(min_square - k^2) 可能会比之前更小
                if (minSquare < k * k) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            System.out.println(ans);
        }
    }
}
