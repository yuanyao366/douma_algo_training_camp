class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        m, n = len(s), len(p)
        dp = [[False] * (n + 1) for _ in range(m + 1)]
        # 1. 空字符串和空字符串是匹配的
        dp[0][0] = True
        # 2. 空字符串和任何单个字符都不匹配
        for i in range(1, m + 1):
            dp[i][0] = False
        # 3. 前前一个元素匹配空字符串并且当前字符是 * ，那么是匹配
        for i in range(1, n + 1):
            if i >= 2 and dp[0][i - 2] and p[i - 1] == '*':
                dp[0][i] = True
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s[i - 1] == p[j - 1] or p[j - 1] == '.':
                    dp[i][j] = dp[i - 1][j - 1]
                elif p[j - 1] == '*':
                    if s[i - 1] == p[j - 2] or p[j - 2] == '.':
                        dp[i][j] = dp[i - 1][j] or dp[i][j - 2]
                    else:
                        dp[i][j] = dp[i][j - 2]
        return dp[m][n]