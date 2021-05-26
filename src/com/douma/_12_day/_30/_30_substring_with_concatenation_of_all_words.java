package com.douma._12_day._30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _30_substring_with_concatenation_of_all_words {
    /* leetcode 30. 串联所有单词的子串
    给定一个字符串 s 和一些 长度相同 的单词 words 。
    找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

    注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。

    示例 1：
    输入：s = "barfoothefoobarman", words = ["foo","bar"]
    输出：[0,9]
    解释：
    从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
    输出的顺序不重要, [9,0] 也是有效答案。

    示例 2：
    输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
    输出：[]

    示例 3：
    输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
    输出：[6,9,12]

    提示：
    1 <= s.length <= 10^4
    s 由小写英文字母组成
    1 <= words.length <= 5000
    1 <= words[i].length <= 30
    words[i] 由小写英文字母组成

     */

    // 暴力解法
    public List<Integer> findSubstring1(String s, String[] words) {
        // 1. 统计每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int wordNum = words.length;
        int totalLen = oneWordLen * wordNum;

        List<Integer> res = new ArrayList<>();
        // 2. 遍历 s 中所有长度等于 totalLen 的子串
        for (int i = 0; i < s.length() - totalLen + 1; i++) {
            // 2.1 拿到等于所有单词长度之和的子串
            String subStr = s.substring(i, i + totalLen);
            // 2.2 统计每个子串中长度为 oneWordLen 所有单词出现的次数
            Map<String, Integer> tmpMap = new HashMap<>();
            for (int j = 0; j < totalLen; j += oneWordLen) {
                String oneWord = subStr.substring(j, j + oneWordLen);
                tmpMap.put(oneWord, tmpMap.getOrDefault(oneWord, 0) + 1);
            }
            // 2.3 如果单词出现的次数和原始 words 中单词出现的次数相同，则符合条件
            if (map.equals(tmpMap)) res.add(i);
        }

        return res;
    }

    // 滑动窗口
    public List<Integer> findSubstring(String s, String[] words) {
        // 1. 统计每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int oneWordLen = words[0].length();
        int wordNum = words.length;

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < oneWordLen; i++) {
            int left = i, right = i;
            int matchedWordCnt = 0;
            Map<String, Integer> windowMap = new HashMap<>();
            while (right + oneWordLen <= s.length()) {
                String currWord = s.substring(right, right + oneWordLen);
                windowMap.put(currWord, windowMap.getOrDefault(currWord, 0) + 1);
                matchedWordCnt++;
                while (windowMap.getOrDefault(currWord, 0) > map.getOrDefault(currWord, 0)) {
                    String leftWord = s.substring(left, left + oneWordLen);
                    windowMap.put(leftWord, windowMap.getOrDefault(leftWord, 0) - 1);
                    left += oneWordLen;
                    matchedWordCnt--;
                }
                if (matchedWordCnt == wordNum) res.add(left);
                right += oneWordLen;
            }
        }

        return res;
    }
}
