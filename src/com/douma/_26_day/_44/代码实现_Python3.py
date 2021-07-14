class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)

        # 状态定义：dp[i][j] 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
        dp = [[False] * (n + 1) for _ in range(m + 1)]

        # 1. 空字符串和空字符串是匹配的
        dp[0][0] = True
        for i in range(1, n + 1):
            if dp[0][i - 1] and p[i - 1] == '*':
                dp[0][i] = True

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s[i - 1] == p[j - 1] or p[j - 1] == '?':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    dp[i][j] = dp[i - 1][j] or dp[i][j - 1]

        return dp[m][n]