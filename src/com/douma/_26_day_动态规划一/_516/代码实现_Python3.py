class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        if len(s) == 0: return 0
        m = len(s)

        # 状态：dp[i][j] 表示在区间 [i...j] 中最长回文子序列的长度
        dp = [[0] * m for _ in range(m)]

        # 初始化，一个字符的最长回文子序列长度是 1
        for i in range(m):
            dp[i][i] = 1

        for i in range(m - 2, -1, -1):
            for j in range(i + 1, m):
                if s[i] == s[j]:
                    dp[i][j] = 2 + dp[i + 1][j - 1]
                else:
                    dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])

        return dp[0][m - 1]