package com.douma._5_day_数学._989;

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
public class _989_add_to_array_form_of_integer {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        int l1 = A.length - 1;
        while (l1 >= 0 || K != 0) {
            int x = l1 < 0 ? 0 : A[l1];
            int y = K == 0 ? 0 : K % 10;

            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;

            l1--;
            K = K / 10;
        }
        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }
}
