package com.douma._6_day_位运算._191;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _191_number_of_1_bits {
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    public int hammingWeight2(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // bug 修复：这里不需要判断最后一位是不是 1，因为会遍历 32 个位
            res += n & 1;
            // 有符号右移和无符号右移都是一样
            // 因为我们只右移 32 位
            n >>= 1;
        }
        return res;
    }

    public int hammingWeight1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }
        return res;
    }
}
