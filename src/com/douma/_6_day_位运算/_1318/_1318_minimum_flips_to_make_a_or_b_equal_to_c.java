package com.douma._6_day_位运算._1318;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _1318_minimum_flips_to_make_a_or_b_equal_to_c {
    public int minFlips(int a, int b, int c) {
        int aOrb = a | b;
        int equal = aOrb ^ c;
        if (equal == 0) return 0;

        int ans = 0;
        for (int i = 0; i < 31; i++) {
            int mask = 1 << i;
            // a | b 和 c 的第 i 位不同，那么至少需要翻转 1 次
            if ((equal & mask) > 0) {
                // 如果 a 和 b 的第 i 位是 1，且 c 的第 i 位是 0，那么需要翻转 2 次
                if ((c & mask) == 0 && ((a & mask) == (b & mask))) {
                    ans += 2;
                } else {
                    ans++;
                }
            }
        }
        return ans;
    }
}
