package com.douma._5_day_数学._9;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _9_palindrome_number {
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        int res = 0;
        while (res < x) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        // 当数字长度为奇数时，我们可以通过 res/10 去除处于个位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，res = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return res == x || x == res / 10;
    }

    public boolean isPalindrome2(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        int res = 0;
        int y = x;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            // 反转整数的时候，可能出现溢出
            // MAX_VALUE = 2^31 - 1 = 2147483647
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10 && pop > 7)) return false; // bug 修复：这里是除以 10
            // MIN_VALUE = -2^31 = -2147483648
            if (res < Integer.MIN_VALUE / 10
                    || (res == Integer.MIN_VALUE / 10 && pop < -8)) return false; // bug 修复：这里是除以 10
            res = res * 10 + pop;
        }

        return y == res;
    }
    public boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
