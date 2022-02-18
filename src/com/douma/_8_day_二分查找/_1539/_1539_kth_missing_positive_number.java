package com.douma._8_day_二分查找._1539;

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
        /*
        解释：right 为什么是 arr.length 而非 arr.length - 1
        因为这里你需要考虑缺失的数字可能是数组最后一个元素的下一个整数，
        所以，这里进行二分的时候，范围必须是 [0....nums.length] 中查找
        比如：[1, 2, 3, 4, 5]  k = 1，
        那么这个时候第 1 个缺失的数字是 6 ，是数组最后一个元素的下一个整数

        就是说你要查找的数字有可能超出数组的范围
         */
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
