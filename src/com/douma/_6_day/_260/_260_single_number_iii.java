package com.douma._6_day._260;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _260_single_number_iii {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        int diff = bitmask & (-bitmask);

        int[] ans = new int[2];
        for (int num : nums) {
            if ((num & diff) != 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }
}
