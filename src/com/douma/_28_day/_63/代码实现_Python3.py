from typing import List


class Solution:
    def uniquePathsWithObstacles1(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        # dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        dp = [[0] * n for _ in range(m)]

        for i in range(m):
            for j in range(n):
                if obstacleGrid[i][j] == 1:
                    dp[i][j] = 0
                    continue
                if i == 0 and j == 0:
                    dp[i][j] = 1
                elif i == 0:
                    dp[i][j] = dp[i][j - 1]
                elif j == 0:
                    dp[i][j] = dp[i - 1][j]
                else:
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j]

        return dp[m - 1][n - 1]

    # 状态压缩
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m, n = len(obstacleGrid), len(obstacleGrid[0])
        # dp[i][j]：表示从点 [0, 0] 到达 [i, j] 的不同路径数
        dp = [0] * n

        for i in range(m):
            for j in range(n):
                if obstacleGrid[i][j] == 1:
                    dp[j] = 0
                    continue
                if i == 0 and j == 0:
                    dp[j] = 1
                elif i == 0:
                    dp[j] = dp[j - 1]
                elif j == 0:
                    dp[j] = dp[j]
                else:
                    dp[j] = dp[j - 1] + dp[j]

        return dp[n - 1]