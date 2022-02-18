package com.douma._5_day_数学;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class TwoNumberAdd {
    public List<Integer> addTwoNum(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        int l1 = nums1.length - 1;
        int l2 = nums2.length - 1;
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 < 0 ? 0 : nums1[l1];
            int y = l2 < 0 ? 0 : nums2[l2];

            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;

            l1--;
            l2--;
        }
        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }
}
