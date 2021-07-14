from typing import List


class Solution:
    def __init__(self):
        self.dirs = [[1, 0], [0, 1]]

    # 3. 记忆化搜索
    def minPathSum1(self, grid: List[List[int]]) -> int:
        MAX_INT = 2**31 - 1
        m, n = len(grid), len(grid[0])
        memo = [[MAX_INT] * n for _ in range(m)]

        def dfs(row, col) -> int:
            if row == m - 1 and col == n - 1:
                return grid[row][col]

            if memo[row][col] != MAX_INT:
                return memo[row][col]

            min_path_sum = MAX_INT
            for dir in self.dirs:
                next_row, next_col = row + dir[0], col + dir[1]
                if next_row < 0 or next_row >= m or next_col < 0 or next_col >= n:
                    continue
                child_min_path_sum = dfs(next_row, next_col)
                min_path_sum = min(min_path_sum, child_min_path_sum)

            memo[row][col] = min_path_sum + grid[row][col]
            return memo[row][col]

        return dfs(0, 0)

    # 4. 动态规划：从终点到起始点
    def minPathSum2(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])

        # 状态定义：dp[i][j] 表示从坐标 (i, j) 到右下角的最小路径和
        dp = [[0] * n for _ in range(m)]

        # 状态初始化
        dp[m - 1][n - 1] = grid[m - 1][n - 1]

        # 状态转移
        for i in range(m - 1, -1, -1):
            for j in range(n - 1, -1, -1):
                if i == m - 1 and j != n - 1:
                    dp[i][j] = grid[i][j] + dp[i][j + 1]
                elif i != m - 1 and j == n - 1:
                    dp[i][j] = grid[i][j] + dp[i + 1][j]
                elif i != m - 1 and j != n - 1:
                    dp[i][j] = grid[i][j] + min(dp[i + 1][j], dp[i][j + 1])

        # 返回结果
        return dp[0][0]

    # 5. 动态规划：从起始点到终点
    def minPathSum3(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])

        # 状态定义：dp[i][j] 表示从 [0,0] 到 [i,j] 的最小路径和
        dp = [[0] * n for _ in range(m)]

        # 状态初始化
        dp[0][0] = grid[0][0]

        # 状态转移
        for i in range(m):
            for j in range(n):
                if i == 0 and j != 0:
                    dp[i][j] = grid[i][j] + dp[i][j - 1]
                elif i != 0 and j == 0:
                    dp[i][j] = grid[i][j] + dp[i - 1][j]
                elif i != 0 and j != 0:
                    dp[i][j] = grid[i][j] + min(dp[i - 1][j], dp[i][j - 1])

        # 返回结果
        return dp[m - 1][n - 1]

    # 6. 动态规划：从起始点到终点 + 状态压缩
    def minPathSum4(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])

        # 状态定义：dp[i] 表示从 (0, 0) 到达第 i - 1 行的最短路径值
        dp = [0] * n

        # 状态初始化
        dp[0] = grid[0][0]

        # 状态转移
        for i in range(m):
            for j in range(n):
                if i == 0 and j != 0:
                    dp[j] = grid[i][j] + dp[j - 1]
                elif i != 0 and j == 0:
                    dp[j] = grid[i][j] + dp[j]
                elif i != 0 and j != 0:
                    dp[j] = grid[i][j] + min(dp[j], dp[j - 1])

        # 返回结果
        return dp[n - 1]

    # 7. 动态规划：从起始点到终点 + 使用输入数组作为状态数组
    def minPathSum(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])

        # 状态转移
        for i in range(m):
            for j in range(n):
                if i == 0 and j != 0:
                    grid[i][j] = grid[i][j] + grid[i][j - 1]
                elif i != 0 and j == 0:
                    grid[i][j] = grid[i][j] + grid[i - 1][j]
                elif i != 0 and j != 0:
                    grid[i][j] = grid[i][j] + min(grid[i - 1][j], grid[i][j - 1])

        # 返回结果
        return grid[m - 1][n - 1]