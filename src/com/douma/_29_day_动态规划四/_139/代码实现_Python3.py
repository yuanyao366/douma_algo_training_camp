from typing import List


class Solution:
    # 记忆化搜索（一）
    def wordBreak1(self, s: str, wordDict: List[str]) -> bool:
        word_dict, memo = set(wordDict), {}

        # 以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
        def dfs(i) -> bool:
            if i == len(s): return True
            if i in memo: return memo[i]
            for end in range(i + 1, len(s) + 1):
                if s[i:end] not in word_dict: continue
                if dfs(end):
                    memo[i] = True
                    return True
            memo[i] = False
            return False

        return dfs(0)

    # 动态规划（一）
    def wordBreak2(self, s: str, wordDict: List[str]) -> bool:
        # 1. 定义状态，dp[i] 表示以第 i 个字符开头的子串是否可以被空格拆分成字典中出现的单词
        word_dict, dp = set(wordDict), [False] * (len(s) + 1)

        # 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[len(s)] = True

        # 如果 dp[j] == true 且 s(i, j] 存在于字典中，那么 dp[i] = true
        for i in range(len(s) - 1, -1, -1):
            for j in range(i + 1, len(s) + 1):
                if dp[j] and s[i:j] in word_dict:
                    dp[i] = True
                    break
        return dp[0]

    # 记忆化搜索（二）
    def wordBreak3(self, s: str, wordDict: List[str]) -> bool:
        word_dict, memo = set(wordDict), {}

        # 以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
        def dfs(i) -> bool:
            if i == 0: return True
            if i in memo: return memo[i]
            for start in range(i - 1, -1, -1):
                if s[start:i] not in word_dict: continue
                if dfs(start):
                    memo[i] = True
                    return True
            memo[i] = False
            return False

        return dfs(len(s))

    # 动态规划（二）
    def wordBreak4(self, s: str, wordDict: List[str]) -> bool:
        # 1. 定义状态，dp[i] 表示以第 i 个字符结尾的子串是否可以被空格拆分成字典中出现的单词
        word_dict, dp = set(wordDict), [False] * (len(s) + 1)

        # 因为空字符串总是字典的一部分。dp 数组剩余的元素都初始化为 false
        dp[0] = True

        # 如果 dp[j] == true 且 s[j, i) 存在于字典中，那么 dp[i] = true
        for i in range(1, len(s) + 1):
            for j in range(0, i):
                if dp[j] and s[j:i] in word_dict:
                    dp[i] = True
                    break
        return dp[len(s)]