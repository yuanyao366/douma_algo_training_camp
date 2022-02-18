package com.douma._6_day_位运算._137;

import java.util.HashSet;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _137_single_number_ii {
    public int singleNumber(int[] nums) {
        int res = 0;
        //int类型有32位，统计每一位1的个数
        for (int i = 0; i < 32; i++) {
            //统计第 i 位中 1 的个数
            int oneCount = 0;
            for (int num : nums) {
                oneCount += (num >> i) & 1;
            }
            //如果1的个数不是3的倍数，说明那个只出现一次的数字
            //的二进制位中在这一位是1
            if (oneCount % 3 == 1) {
                res |= 1 << i;
            }
        }
        return res;
    }

    public int singleNumber2(int[] nums) {
        int once = 0, twice = 0;
        for (int num : nums) {
            once = (once ^ num) & ~twice;
            twice = (twice ^ num) & ~once;
        }
        return once;
    }
    public int singleNumber1(int[] nums) {
        Set<Integer> once = new HashSet<>();
        Set<Integer> twice = new HashSet<>();

        for (int n : nums) {
            if (once.contains(n)) {
                once.remove(n);
            } else if (!twice.contains(n)) {
                once.add(n);
            }

            if (twice.contains(n)) {
                twice.remove(n);
            } else if (!once.contains(n)) {
                twice.add(n);
            }
        }

        return once.iterator().next();
    }
}
