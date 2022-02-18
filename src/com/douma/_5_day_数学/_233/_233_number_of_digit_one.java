package com.douma._5_day_数学._233;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _233_number_of_digit_one {
    // O(logn)
    public int countDigitOne(int n) {
        int count = 0;
        // bug 修复：i 应该是长整型，要不然会溢出
        for (long i = 1; i <= n; i *= 10) {
            // (n/10)*1 + min(max((n%10 - 1 + 1), 0), 1)
            // (n/100)*10 + min(max((n%100 - 10 + 1), 0), 10)
            // (n/1000)*100 + min(max((n%1000 - 100 + 1), 0), 100)
            long divider = i * 10;
            count += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return count;
    }
    // 暴力超时
    public int countDigitOne1(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            int tmp = 0;
            for (char c : s.toCharArray()) {
                if (c == '1') tmp++;
            }
            res += tmp;
        }
        return res;
    }
}
