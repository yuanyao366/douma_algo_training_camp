package com.douma._6_day._190;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _190_reverse_bits {
    private static final int M1 = 0x55555555; // 01010101010101010101010101010101
    private static final int M2 = 0x33333333; // 00110011001100110011001100110011
    private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
    private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

    public int reverseBits(int n) {
        n = (n >>> 1) & M1 | (n & M1) << 1; // 每两位进行交换
        n = (n >>> 1) & M2 | (n & M2) << 1; // 每四位进行交换
        n = (n >>> 1) & M4 | (n & M4) << 1; // 每八位进行交换
        n = (n >>> 1) & M8 | (n & M8) << 1; // 每十六位进行交换
        // 32 位的前后交换
        return n >>> 16 | n << 16;
    }

    public int reverseBits1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>= 1;
        }
        return res;
    }
}
