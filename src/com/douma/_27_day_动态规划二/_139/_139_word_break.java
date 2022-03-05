package com.douma._27_day_动态规划二._139;

import java.util.List;

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
    // 完全背包问题：
    // 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s 的组合
    /*
    物品：单词数组
    背包重量：字符串
    背包价值：单词是否组合成功字符串

    dp[i][c],表示前 i 个单词，是否可以组成长度为 c 的字符串，
    由于每个字符串都需所有的单词组合一遍，因此将背包重量的循环放到物品个数的循环之前。

    每个物品都有放入和不放入的情况
    当物品不放入背包
        dp[i][c]=dp[i - 1][c]
    当物品放入背包
        dp[i][c]=dp[i][c - w.size()]
     */
    // 压缩物品维度
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
        boolean[] dp = new boolean[s.length() + 1];

        dp[0] = true;

        // 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
        // bug 修复：i 可以等于字符串的长度，因为 i 就是表示子串的长度
        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int wordLen = word.length();
                if (i >= wordLen && s.substring(i - wordLen, i).equals(word)) {
                    dp[i] = dp[i] || dp[i - wordLen];
                }
            }
        }

        return dp[s.length()];
    }
}
