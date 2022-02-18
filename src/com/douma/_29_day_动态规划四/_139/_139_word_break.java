package com.douma._29_day_动态规划四._139;

import java.util.HashMap;
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
public class _139_word_break {
    /* 139. 单词拆分
    给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，
    判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

    说明：
    拆分时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。

    示例 1：
    输入: s = "leetcode", wordDict = ["leet", "code"]
    输出: true
    解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。

    示例 2：
    输入: s = "applepenapple", wordDict = ["apple", "pen"]
    输出: true
    解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
         注意你可以重复使用字典中的单词。

    示例 3：
    输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出: false

     */

    // 1. 记忆化搜索(一)
    public boolean wordBreak1(String s, List<String> wordDict) {
        return dfs(s, new HashSet<>(wordDict), 0, new HashMap<>());
    }

    // 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
    private boolean dfs(String s, Set<String> wordDict,
                        int i, HashMap<Integer, Boolean> memo) {
        if (i == s.length()) return true;
        if (memo.containsKey(i)) return memo.get(i);
        for (int end = i + 1; end <= s.length(); end++) {
            if (!wordDict.contains(s.substring(i, end))) continue;

            if (dfs(s, wordDict, end, memo)) {
                memo.put(i, true);
                return true;
            }
        }

        memo.put(i, false);
        return false;
    }

    // 2. 动态规划(一)
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        // 1. 定义状态，dp[i] 表示以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];

        // 2. 初始化
        // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[s.length()] = true;

        // 3. 状态转移方程
        // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (!dict.contains(s.substring(i, j))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[0];
    }

    // 3. 记忆化搜索(二)
    public boolean wordBreak3(String s, List<String> wordDict) {
        return dfs2(s, new HashSet<>(wordDict), s.length(), new HashMap<>());
    }

    // 以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
    private boolean dfs2(String s, Set<String> wordDict,
                        int i, HashMap<Integer, Boolean> memo) {
        if (i == 0) return true;
        if (memo.containsKey(i)) return memo.get(i);
        for (int start = i - 1; start >= 0; start--) {
            if (!wordDict.contains(s.substring(start, i))) continue;

            if (dfs2(s, wordDict, start, memo)) {
                memo.put(i, true);
                return true;
            }
        }

        memo.put(i, false);
        return false;
    }

    // 4. 动态规划(二)
    public boolean wordBreak4(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        // 1. 定义状态，dp[i] 表示以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
        boolean[] dp = new boolean[s.length() + 1];

        // 2. 初始化
        // 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[0] = true;

        // 3. 状态转移方程
        // 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (!dict.contains(s.substring(j, i))) continue;
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}
