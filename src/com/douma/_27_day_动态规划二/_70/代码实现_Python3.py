class Solution:
    # 1. 记忆化搜索
    def climbStairs1(self, n: int) -> int:
        memo = [-1] * (n + 1)

        def dfs(n) -> int:
            if n == 1 or n == 2:
                return n
            if memo[n] != -1:
                return memo[n]
            left, right = dfs(n - 1), dfs(n - 2)
            memo[n] = left + right
            return memo[n]

        return dfs(n)

    # 2. 动态规划
    def climbStairs2(self, n: int) -> int:
        if n == 1 or n == 2:
            return n
        dp = [0] * (n + 1)
        dp[1], dp[2] = 1, 2
        for i in range(3, n + 1):
            dp[i] = dp[i - 1] + dp[i - 2]
        return dp[n]

    # 2. 动态规划 + 状态压缩
    def climbStairs(self, n: int) -> int:
        if n == 1 or n == 2:
            return n
        pre, curr = 1, 2
        for i in range(3, n + 1):
            sum = pre + curr
            pre = curr
            curr = sum
        return curr