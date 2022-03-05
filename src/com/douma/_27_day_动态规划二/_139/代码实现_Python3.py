from typing import List


class Solution:
    # 完全背包问题：
    # 在 wordDict 中可重复的选择字符串组合，看看是否存在可以组成字符串 s
    """
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
    """
    def wordBreak1(self, s: str, wordDict: List[str]) -> bool:
        # dp[i]: 表示前 i 个字符组成的子串是否可以被 wordDict 中的字符串组合而成
        dp = [False] * (len(s) + 1)
        dp[0] = True

        # 注意：这里的组合的顺序是任意的，所以先选择字符，再选择每个字符串
        for i in range(len(s) + 1):
            for word in wordDict:
                word_len = len(word)
                if i >= word_len and s[i - word_len:i] == word:
                    dp[i] = dp[i] or dp[i - word_len]

        return dp[len(s)]