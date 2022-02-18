package com.douma._6_day_位运算._461;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _461_hamming_distance {
    public int hammingDistance(int x, int y) {
        // 使用异或计算 x 和 y 的不同位，结果中位为 1 ，说明这位不同
        int diff = x ^ y;

        // 计算 diff 中位 1 个数
        int res = 0;
        while (diff != 0) {
            // bug 修复：这里是 &，去掉最后一个 1
            diff = diff & (diff - 1);
            res++;
        }

        return res;
    }
}
