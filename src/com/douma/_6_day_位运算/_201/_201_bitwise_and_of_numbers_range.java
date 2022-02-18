package com.douma._6_day_位运算._201;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _201_bitwise_and_of_numbers_range {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right = right & (right - 1);
        }
        return right;
    }

    public int rangeBitwiseAnd2(int left, int right) {
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        return left << shift;
    }

    // 0 <= left <= right <= 2^31 - 1
    // 超时
    public int rangeBitwiseAnd1(int left, int right) {
        int ans = left;
        for (int i = left + 1; i <= right; i++) {
            ans &= i;
        }
        return ans;
    }
}
