package com.douma._12_day_滑动窗口._567;

import java.util.Arrays;

public class _567_permutation_in_string {
    /* leetcode 567. 字符串的排列
    给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

    换句话说，第一个字符串的排列之一是第二个字符串的 子串 。

    示例 1：
    输入: s1 = "ab" s2 = "eidbaooo"
    输出: True
    解释: s2 包含 s1 的排列之一 ("ba").

    示例 2：
    输入: s1= "ab" s2 = "eidboaoo"
    输出: False

    提示：
    输入的字符串只包含小写字母
    两个字符串的长度都在 [1, 10,000] 之间

     */

    public boolean checkInclusion1(String s1, String s2) {
        /*
        输入: s1 = "ab" s2 = "eidbaooo"
        s1:
            a->1
            b->1
        s2：
            b->1
            a->1
         */
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; i++) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; i++) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }

    // 滑动窗口
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (n > m) {
            return false;
        }

        // 先统计字符串 s1 中每个字符出现的次数
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s1.charAt(i) - 'a'];
        }

        int left = 0, right = 0;
        while (right < m) {
            int x = s2.charAt(right) - 'a';
            cnt[x]--;
            while (cnt[x] < 0) {
                // 通过缩减窗口使得 cnt[x] 不为负数
                cnt[s2.charAt(left) - 'a']++;
                left++;
            }
            // 到现在为止，当前窗口中字符的 cnt 值都为 0（不包含 s1 里面的字符）
            // 如果窗口的长度等于 n 的话，那么当前窗口中的 cnt 的值都是 0
            if (right - left + 1 == n) return true;
            // bug 修复：right 需要往前走
            right++;
        }
        return false;
    }

}
