from typing import List


class Solution:
    # 1. 记忆化搜索
    def uniquePaths(self, m: int, n: int) -> int:
        memo = [[-1] * n for _ in range(m)]

        def dfs(i, j) -> int:
            if i == m - 1 and j == n - 1:
                return 1
            if i == m or j == n:
                return 0
            if memo[i][j] != -1:
                return memo[i][j]

            left, right = dfs(i, j + 1), dfs(i + 1, j)
            memo[i][j] = left + right
            return memo[i][j]

        return dfs(0, 0)

    # 2. 动态规划（左上到右下）
    def uniquePaths2(self, m: int, n: int) -> int:
        # dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
        dp = [[-1] * n for _ in range(m)]
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if i == m - 1 or j == n - 1:
                    dp[i][j] = 1
                else:
                    dp[i][j] = dp[i + 1][j] + dp[i][j + 1]
        return dp[0][0]

    # 3. 动态规划（左上到右下）- 状态压缩
    def uniquePaths3(self, m: int, n: int) -> int:
        # dp[i][j]：表示从位置 [i, j] 到 [m - 1. n - 1] 的路径数
        dp = [-1] * n
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if i == m - 1 or j == n - 1:
                    dp[j] = 1
                else:
                    dp[j] = dp[j] + dp[j + 1]
        return dp[0]

    # 4. 动态规划（右下到左上）
    def uniquePaths4(self, m: int, n: int) -> int:
        # dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        dp = [[-1] * n for _ in range(m)]
        for i in range(m):
            for j in range(n):
                if i == 0 or j == 0:
                    dp[i][j] = 1
                else:
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        return dp[m - 1][n - 1]

    # 4. 动态规划（右下到左上）- 状态压缩
    def uniquePaths(self, m: int, n: int) -> int:
        # dp[i][j]：表示从位置[0, 0] 到 [i, j] 的路径数
        dp = [-1] * n
        for i in range(m):
            for j in range(n):
                if i == 0 or j == 0:
                    dp[j] = 1
                else:
                    dp[j] = dp[j] + dp[j - 1]
        return dp[n - 1]