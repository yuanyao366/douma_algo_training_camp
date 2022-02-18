package com.douma._8_day_二分查找._69;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _69_sqrtx {
    // 二分查找
    // 时间复杂度：O(logx)
    public int mySqrt(int x) {
        int left = 0, right = x;
        int ans = -1;
        while (left <= right) {
            int k = left + (right - left) / 2;
            if ((long)k * k <= x) {
                ans = k;
                left = k + 1;
            } else {
                right = k - 1;
            }
        }
        return ans;
    }
    // 时间复杂度：O(x)
    public int mySqrt1(int x) {
        int ans = -1;
        for (int k = 0; k <= x; k++) {
            if ((long)k * k <= x) {
                ans = k;
            }
        }
        return ans;
    }
}
