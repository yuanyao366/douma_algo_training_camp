class Solution:
    # 两个字符串的最小 ASCII 删除和等于两个字符串所有字母的和减去两个字符串最大公共子序列所有字母的和
    # 问题转化成 LCS 问题
    def minimumDeleteSum(self, s1: str, s2: str) -> int:

        m, n = len(s1), len(s2)

        sum_ = 0
        for c in s1:
            sum_ += ord(c)
        for c in s2:
            sum_ += ord(c)

        # dp[i][j]：s1 前 i 个字符和 s2 前 j 个字符【最长公共子序列长度】
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s1[i - 1] == s2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1] + ord(s1[i - 1])
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

        return sum_ - 2 * dp[m][n]