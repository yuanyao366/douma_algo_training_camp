package com.douma._12_day_滑动窗口._76;

import java.util.ArrayList;
import java.util.List;

public class _76_minimum_window_substring {
    /* 76. 最小覆盖子串
    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
    如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

    注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

    示例 1：
    输入：s = "ADOBECODEBANC", t = "ABC"
    输出："BANC"

    示例 2：
    输入：s = "a", t = "a"
    输出："a"

    提示：
    1 <= s.length, t.length <= 10^5
    s 和 t 由英文字母组成
     
    进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？

     */

    public String minWindow1(String s, String t) {
        // 统计字符串 t 中每个字符出现的次数
        int[] cntT = new int[60];
        // 统计 t 中不同的字符数
        int uniqueCharsInT = 0;
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) uniqueCharsInT++;
            cntT[c - 'A']++;
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];
        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;
        int[] ans = {-1, 0, 0};
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }
            while (left <= right && matchedChars == uniqueCharsInT) {
                // 尝试缩减窗口，因为我们想找到最短符合条件的子串
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                char leftChar = s.charAt(left);
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public String minWindow2(String s, String t) {
        // 统计字符串 t 中每个字符出现的次数
        int[] cntT = new int[60];
        // 统计 t 中不同的字符数
        int uniqueCharsInT = 0;
        for (char c : t.toCharArray()) {
            if (cntT[c - 'A'] == 0) uniqueCharsInT++;
            cntT[c - 'A']++;
        }

        // 在 s 中拿到是 t 中的字符，及其在 s 中的位置
        List<Pair<Integer, Character>> filteredS =
                new ArrayList<Pair<Integer, Character>>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cntT[c - 'A'] > 0) {
                filteredS.add(new Pair<>(i, c));
            }
        }

        int left = 0, right = 0;
        // 窗口中每个字符出现的次数
        int[] windowCntS = new int[60];
        // 记录当前窗口中字符出现的次数和 t 中字符出现次数相等的字符数
        int matchedChars = 0;
        int[] ans = {-1, 0, 0};
        while (right < filteredS.size()) {
            char rightChar = filteredS.get(right).getValue();
            int rightCharIndex = rightChar - 'A';
            windowCntS[rightCharIndex]++;

            if (windowCntS[rightCharIndex] == cntT[rightCharIndex]) {
                matchedChars++;
            }
            while (left <= right && matchedChars == uniqueCharsInT) {
                // 尝试缩减窗口，因为我们想找到最短符合条件的子串
                int end = filteredS.get(right).getKey();
                int start = filteredS.get(left).getKey();
                if (ans[0] == -1 || end - start + 1 < ans[0]) {
                    ans[0] = end - start + 1;
                    ans[1] = start;
                    ans[2] = end;
                }

                char leftChar = filteredS.get(left).getValue();
                int leftCharIndex = leftChar - 'A';
                windowCntS[leftCharIndex]--;
                if (windowCntS[leftCharIndex] < cntT[leftCharIndex]) {
                    matchedChars--;
                }
                left++;
            }
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    private class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
