from typing import List


class Solution:
    # 记忆化搜索
    def PredictTheWinner1(self, nums: List[int]) -> bool:
        m, MIN_INT = len(nums), -2**31
        memo = [[MIN_INT] * m for _ in range(m)]

        # 玩家 1 在区间 [i, j] 内可以赢的最多的分
        def dfs(i, j) -> int:
            if i == j: return nums[i]
            if memo[i][j] != MIN_INT:
                return memo[i][j]
            pick_i = nums[i] - dfs(i + 1, j)
            pick_j = nums[j] - dfs(i, j - 1)
            memo[i][j] = max(pick_i, pick_j)
            return memo[i][j]

        return dfs(0, m - 1) >= 0

    # 动态规划
    def PredictTheWinner(self, nums: List[int]) -> bool:
        m, MIN_INT = len(nums), -2**31

        # dp[i][j] 表示玩家 1 在区间 [i, j] 之内可以赢的最多的分
        dp = [[MIN_INT] * m for _ in range(m)]

        for i in range(0, m):
            dp[i][i] = nums[i]

        for i in range(m - 2, -1, -1):
            for j in range(i + 1, m):
                dp[i][j] = max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1])

        return dp[0][m - 1] >= 0