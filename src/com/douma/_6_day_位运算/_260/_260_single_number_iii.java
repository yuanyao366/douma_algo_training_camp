package com.douma._6_day_位运算._260;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _260_single_number_iii {
    public int[] singleNumber(int[] nums) {
        // 把所有的元素进行异或操作，最终得到一个异或值。因为是不同的两个数字，所以这个值必定不为 0；
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // 取异或值最后一个二进制位为 1 的数字作为 diff，如果是 1 则表示两个数字在这一位上不同。
        int diff = bitmask & (-bitmask);

        // 通过与这个 diff 进行与操作，如果为 0 的分为一个数组，为 1 的分为另一个数组
        // 这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”
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
