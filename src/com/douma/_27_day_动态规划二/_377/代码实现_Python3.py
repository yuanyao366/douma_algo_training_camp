from typing import List


class Solution:
    # 完全背包问题：
    #      在 nums 列表中可重复的选择数字组合，使得组合之和等于 target
    # 请注意，顺序不同的序列被视作不同的组合。
    def combinationSum4(self, nums: List[int], target: int) -> int:
        # dp[i]：表示从 nums 中找到总和等于 i 的元素组合的个数
        dp = [0] * (target + 1)
        dp[0] = 1
        for c in range(1, target + 1):
            for num in nums:
                if num <= c:
                    dp[c] = dp[c] + dp[c - num]
        return dp[target]