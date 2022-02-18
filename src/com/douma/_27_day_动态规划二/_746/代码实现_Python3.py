from typing import List


class Solution:
    # 1. 记忆化搜索
    def minCostClimbingStairs1(self, cost: List[int]) -> int:
        n = len(cost)
        memo = [-1] * (n + 1)

        def dfs(i) -> int:
            if i == 0 or i == 1:
                return 0
            if memo[i] != -1:
                return memo[i]
            left, right = dfs(i -1), dfs(i - 2)
            memo[i] = min(left + cost[i - 1], right + cost[i - 2])
            return memo[i]

        return dfs(n)

        # 2. 动态规划
    def minCostClimbingStairs2(self, cost: List[int]) -> int:
        n = len(cost)
        dp = [-1] * (n + 1)
        dp[0] = dp[1] = 0
        for i in range(2, n + 1):
            dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2])
        return dp[n]

    # 3. 动态规划 + 状态压缩
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        n = len(cost)
        prev = curr = 0
        for i in range(2, n + 1):
            sum = min(curr + cost[i - 1], prev + cost[i - 2])
            prev, curr = curr, sum
        return curr