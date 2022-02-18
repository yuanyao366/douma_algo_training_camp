package com.douma._29_day_动态规划四._140;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @官方网站 : https://douma.ke.qq.com
 * @微信公众号 : 抖码课堂
 * @官方微信号 : bigdatatang01
 * 抖码算法，让算法学习变的简单有趣
 * @作者 : 老汤
 */
public class _140_word_break_ii {
    /* 140. 单词拆分 II
    给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，
    在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
    返回所有这些可能的句子。

    说明：
    分隔时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。

    示例 1：
    输入:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    输出:
    [
      "cats and dog",
      "cat sand dog"
    ]

    示例 2：
    输入:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    输出:
    [
      "pine apple pen apple",
      "pineapple pen apple",
      "pine applepen apple"
    ]
    解释: 注意你可以重复使用字典中的单词。

    示例 3：
    输入:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出:
    []
     */

    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), 0);
    }

    private List<String> dfs(String s, Set<String> wordDict, int i) {
        List<String> res = new ArrayList<>();
        if (i == s.length()) {
            res.add("");
            return res;
        }

        for (int end = i + 1; end <= s.length(); end++) {
            String word = s.substring(i, end);
            if (!wordDict.contains(word)) continue;

            List<String> list = dfs(s, wordDict, end);
            for (String str : list) {
                res.add(word + (str.equals("") ? "" : " ") + str);
            }
        }

        return res;
    }
}
