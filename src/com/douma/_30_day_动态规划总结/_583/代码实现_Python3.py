class Solution:
    # 为了求得最少删除次数，我们可以求出串 word1 和串 word2 最长公共子序列，我们记为 lcs。
    # 如果我们能求得 lcs 的值，我们可以轻易地求出答案，为 m + n - 2 * lcs。
    # 这里 m 和 n 分别是给定字符串 word1 和 word2 的长度。

    # 问题转化为 LCS 问题
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)

        # dp[i][j]：word1 前 i 个字符和 word2 前 j 个字符【最长公共子序列长度】
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

        return m + n - 2 * dp[m][n]