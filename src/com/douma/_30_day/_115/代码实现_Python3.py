class Solution:
    def numDistinct(self, s: str, t: str) -> int:
        m, n = len(s), len(t)
        if m < n: return 0

        # dp[i][j]：表示 s 的前 i 个字符中组成 t 的前 j 个字符的最多个数
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(m + 1):
            # s 的前 i 个字符中始终包含一个 空字符串
            dp[i][0] = 1

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s[i - 1] == t[j - 1]:
                    # s[i] == t[j]的时候, s[i] 可以选择自己是否跟 t[j]匹配
                    # 如果匹配，那么 dp[i][j] 其中一部分数量就是 dp[i-1][j-1]
                    # 如果选择不匹配（这样可以让前面的字符跟t[j]匹配，毕竟 t 短的,s 长) dp[i][j] 另外一部分就是 dp[i - 1][j]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
                else:
                    dp[i][j] = dp[i - 1][j]

        return dp[m][n]