from typing import List


class Solution:
    def minimumTotal1(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        # dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
        dp = [[0] * n for _ in range(n)]

        for i in range(n):
            dp[n - 1][i] = triangle[n - 1][i]

        for i in range(n - 2, -1, -1):
            for j in range(i + 1):
                dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]

        return dp[0][0]

    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        # dp[i][j] 表示从点 [i, j] 到底边的最小路径和。
        dp = [0] * n

        for i in range(n):
            dp[i] = triangle[n - 1][i]

        for i in range(n - 2, -1, -1):
            for j in range(i + 1):
                dp[j] = min(dp[j], dp[j + 1]) + triangle[i][j]

        return dp[0]