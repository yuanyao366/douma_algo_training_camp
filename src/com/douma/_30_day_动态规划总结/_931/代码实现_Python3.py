from typing import List


class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        n = len(matrix)

        # dp[i][j]：表示元素 [i, j] 的下降路径最小和
        dp = [[0] * n for _ in range(n)]

        # 最后一行所有元素的下降路径最小和就是元素值本身
        for j in range(n):
            dp[n - 1][j] = matrix[n - 1][j]

        for i in range(n - 2, -1, -1):
            for j in range(n):
                # dp[i][j] = min(dp[i + 1][j], dp[i + 1][j - 1], dp[i + 1][j + 1])
                minSum = dp[i + 1][j]
                if j > 0:
                    minSum = min(minSum, dp[i + 1][j - 1])
                if j + 1 < n:
                    minSum = min(minSum, dp[i + 1][j + 1])
                dp[i][j] = minSum + matrix[i][j]

        return min(dp[0])