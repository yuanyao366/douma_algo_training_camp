package com.douma.笔试代码.bilibili._20211013;

import java.util.Scanner;

/**
 * 抖码算法，让算法学习变的简单有趣
 *
 * @作者 : 老汤
 */
public class _2_OpenChest {
    // 时间复杂度：O(nlogm), n 是数组的大小，m 最大值为 10^9
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        if (n == 1 && k == n - 1) {
            System.out.println(0);
            System.exit(0);
        }

        int[] b = new int[n];

        int maxAbsDiff = 0;
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
            if (i >= 1 && Math.abs(b[i] - b[i - 1]) > maxAbsDiff) {
                maxAbsDiff = Math.abs(b[i] - b[i - 1]);
            }
        }

        int low = 0, high = maxAbsDiff;
        while (low < high) {
            int mid = low + (high - low) / 2;

            // 计算要使得最大差绝对值 = mid 的话，最少需要修改的次数
            int cnt = getLeastModifyTimes(n, b, mid);

            // 如果使得最大差绝对值 = mid，需要修改的最小次数大于 k 的话
            // 说明在最多修改 k 次的情况下，最小的【最大差绝对值】肯定要比 mid 大
            // 所以需要去右半区查找大一点的值
            if (cnt > k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        System.out.println(low);
    }

    private static int getLeastModifyTimes(int n, int[] b, int absDiff) {
        int cnt = 0, i = 1;
        while (i < n){
            if (Math.abs(b[i] - b[i - 1]) > absDiff) {
                // 如果出现差绝对值大于 mid 的话，就尝试修改一次
                // 修改的话，尽可能修改最少的次数：
                //      如果后一个元素减去前一个元素的绝对值小于 2 倍的 mid
                //      那么就修改了当前元素值，就没必要修改后面的一个元素，这里有点贪心的意思
                if (i < n - 1 && Math.abs(b[i + 1] - b[i - 1]) <= 2 * absDiff) {
                    i += 2;
                } else {
                    i++;
                }
                cnt++;
            } else {
                i++;
            }
        }
        return cnt;
    }
}
