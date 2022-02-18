package com.douma._6_day_位运算._476;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _476_number_complement {
    public int findComplement(int num) {
        int mask = ~0;
        while ((num & mask) > 0) mask <<= 1;
        return ~mask ^ num;
    }
}
