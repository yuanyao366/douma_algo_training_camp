class Solution:
    # 完全背包问题
    # 完全平方数最小为 1，最大为 sqrt(n)
    # 也就是我们要从 nums = [1, 2, ..., sqrt(n)] 数组里选出几个数，令其平方和为 target = n。
    # 转化为是否可以用 nums 中的数(可重复选用)组合和成 n
    def numSquares(self, n: int) -> int:
        # dp[i] 表示和为 i 的 nums 组合中完全平方数最少个数
        MAX_INT = 2**31 - 1
        dp = [MAX_INT] * (n + 1)
        dp[0] = 0
        for num in range(1, int(math.sqrt(n) + 1)):
            for c in range(num * num, n + 1):
                dp[c] = min(dp[c], dp[c - num * num] + 1)
        return dp[n]