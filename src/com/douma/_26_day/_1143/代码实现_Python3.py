class Solution:
    def longestCommonSubsequence1(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)

        # dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if text1[i - 1] == text2[j - 1]:
                    dp[i][j] = 1 + dp[i - 1][j - 1]
                else:
                    dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

        return dp[m][n]

    # 状态空间压缩
    def longestCommonSubsequence2(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)

        if m < n:
            return self.longestCommonSubsequence2(text2, text1)

        # dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        # 当前的状态依赖于前一行和当前行两行的状态值
        dp = [[0] * (n + 1) for _ in range(2)]

        for i in range(1, m + 1):
            curr_row = i % 2
            for j in range(1, n + 1):
                if text1[i - 1] == text2[j - 1]:
                    dp[curr_row][j] = 1 + dp[1 - curr_row][j - 1]
                else:
                    dp[curr_row][j] = max(dp[1 - curr_row][j], dp[curr_row][j - 1])

        return dp[m % 2][n]

    # 状态空间继续压缩
    def longestCommonSubsequence(self, text1: str, text2: str) -> int:
        m, n = len(text1), len(text2)

        if m < n:
            return self.longestCommonSubsequence(text2, text1)

        # dp[i][j]：text1 前 i 个字符和 text2 前 j 个字符【最长公共子序列长度】
        # 当前的状态依赖于前一行和当前行两行的状态值，我们可以使用两个变量来存储
        dp = [0] * (n + 1)

        for i in range(1, m + 1):
            pre_row = pre_row_pre_col = 0
            for j in range(1, n + 1):
                pre_row_pre_col = pre_row
                pre_row = dp[j]
                if text1[i - 1] == text2[j - 1]:
                    dp[j] = 1 + pre_row_pre_col
                else:
                    dp[j] = max(dp[j - 1], pre_row)

        return dp[n]