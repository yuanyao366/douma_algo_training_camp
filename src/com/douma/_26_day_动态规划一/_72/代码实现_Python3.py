class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        if m * n == 0: return m + n

        # dp[i][j] 表示 word1 前 i 个字符转换成 word2 前 j 个字符花的最少操作数[即编辑距离]
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(0, m + 1):
            dp[i][0] = i
        for i in range(0, n + 1):
            dp[0][i] = i

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]
                else:
                    insert_num = 1 + dp[i][j - 1]
                    delete_num = 1 + dp[i - 1][j]
                    replace_num = 1 + dp[i - 1][j - 1]
                    dp[i][j] = min(insert_num, min(delete_num, replace_num))

        return dp[m][n]