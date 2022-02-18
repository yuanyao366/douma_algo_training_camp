package com.douma._12_day_滑动窗口._3;

import java.util.*;

public class _3_longest_substring_without_repeating_characters {
    /* leetcode 3. 无重复字符的最长子串
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    示例 1:
    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

    示例 2:
    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

    示例 3:
    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

    示例 4:
    输入: s = ""
    输出: 0
     
    提示：
    0 <= s.length <= 5 * 10^4
    s 由英文字母、数字、符号和空格组成

     */
    // 1. 暴力解法：遍历数组的所有的区间，然后找到最长没有重复字符的区间
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(n)
    // 会超时
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    private boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            }
            set.add(s.charAt(i));
        }
        return true;
    }

    // 暴力解法存在重复计算，同一个子串会进行多次判断是否存在重复字符
    // 我们可以做如下的优化：
    // 1. 如果 s[i, j) 子串没有重复字符的话，那么如果要判断 s[i, j] 没有重复字符的话
    // 2. 我们只需要判断 s[i, j) 中是否存在 s[j] 即可


    // 2. 滑动窗口
    // 时间复杂度：O(2n) = O(n)，最坏的情况是 left 和 right 都遍历了一遍字符串
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        Set<Character> window = new HashSet<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            while (window.contains(rightChar)) {
                window.remove(s.charAt(left));
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            window.add(rightChar);
            right++;
        }

        return maxLen;
    }

    // 以上当在窗口中存在重复字符，是一个一个字符的缩小窗口
    // 我们可以通过记住每个字符在字符串中的索引，当遇到重复字符的时候，就可以直接跳到重复字符的后面

    // 3. 优化后的滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightCharIndex = window.getOrDefault(rightChar, 0);
            // 下面这样代码的详细解释请见：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4JB1P
            left = Math.max(left, rightCharIndex);
            maxLen = Math.max(maxLen, right - left + 1);
            window.put(rightChar, right + 1);
            right++;
        }

        return maxLen;
    }

    // 4. 追求程序的极致性能
    // s 由英文字母、数字、符号和空格组成
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int maxLen = 1;

        int left = 0, right = 0;
        int[] window = new int[128];
        while (right < n) {
            char rightChar = s.charAt(right);
            int rightCharIndex = window[rightChar];
            left = Math.max(left, rightCharIndex);
            maxLen = Math.max(maxLen, right - left + 1);
            window[rightChar] = right + 1;
            right++;
        }

        return maxLen;
    }
}
