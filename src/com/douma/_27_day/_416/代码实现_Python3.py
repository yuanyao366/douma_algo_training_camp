from typing import List


class Solution:
    # 先计算得到数组的总和为 sum，然后将 sum / 2 得到一半，记为 target
    # 问题转变为 0-1 背包问题：在数组 nums 中不可重复的选择数字组合，是否存在和等于 target 的组合呢？
    def canPartition(self, nums: List[int]) -> bool:
        if sum(nums) % 2 == 1: return False

        target = sum(nums) // 2

        # dp[c] : 表示从 nums 中是否可以找到总和等于 c 的元素组合
        dp = [False] * (target + 1)
        dp[0] = True

        for num in nums:
            for c in range(target, num - 1, -1):
                dp[c] = dp[c] or dp[c - num]

        return dp[target]