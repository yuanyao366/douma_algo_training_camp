package com.douma._24_day_贪心算法一._680;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _680_valid_palindrome_ii {
    /* 680. 验证回文字符串 Ⅱ
    给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

    示例 1:
    输入: "aba"
    输出: true

    示例 2:
    输入: "abca"
    输出: true

    注意:
    字符串只包含从 a-z 的小写字母。字符串的最大长度是 50000。

     */

    // 暴力解法
    // 时间复杂度：O(n^2) 超时
    // 空间复杂度：O(1)
    public boolean validPalindrome1(String s) {
        // 删除逐个字符，然后判断， i = -1 表示不删除任何字符
        for (int i = -1; i < s.length(); i++) {
            boolean isPalindrome = true;
            int left = 0, right = s.length() - 1;
            while (left < right) {
                // 删除索引 i 对应的字符
                if (left == i) left++;
                if (right == i) right--;

                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                } else {
                    isPalindrome = false;
                    break;
                }
            }
            if (isPalindrome) return true;
        }
        return false;
    }

    // 贪心策略：只有在开头和结尾两个字符不相等的时候，才去尝试删除开头或者结尾任一个字符
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // 要么删除 left 指向的字符，要么删除 right 指向的字符
                // 然后再判断剩余的字符是否是回文
                return validPalindrome(s, left + 1, right) ||
                        validPalindrome(s, left, right - 1);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int left, int right) {
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
