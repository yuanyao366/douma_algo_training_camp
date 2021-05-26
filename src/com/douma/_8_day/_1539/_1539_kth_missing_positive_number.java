package com.douma._8_day._1539;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1539_kth_missing_positive_number {
    // 元素 a[i] 之前缺失的正整数的个数为：a[i] - i - 1
    public int findKthPositive(int[] arr, int k) {
        if (arr[0] > k) return k;
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] - mid - 1 < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left
        int leftMissCnt = arr[left - 1] - (left - 1) - 1;
        return arr[left - 1] + (k - leftMissCnt);
    }
    // 时间复杂度 ： O(n + k)
    public int findKthPositive1(int[] arr, int k) {
        int currNum = 1;
        int missCnt = 0;
        int lastMissNum = -1;

        int i = 0;
        while (missCnt < k) {
            if (currNum == arr[i]) {
                // bug 修复：注意 i 的边界
                i = (i + 1 < arr.length) ? i + 1 : i;
            } else {
                missCnt++;
                lastMissNum = currNum;
            }
            currNum++;
        }

        return lastMissNum;
    }
}
