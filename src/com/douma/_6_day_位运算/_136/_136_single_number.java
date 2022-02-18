package com.douma._6_day_位运算._136;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _136_single_number {
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public int singleNumber1(int[] nums) {
        Set<Integer> single = new HashSet<>();
        for (int num : nums) {
            if (single.contains(num)) {
                single.remove(num);
            } else {
                single.add(num);
            }
        }
        return single.iterator().next();
    }
}
