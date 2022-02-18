from typing import List


class Solution:
    # 0 - 1 背包问题
    # 假设数组中所有数字的总和为 sum
    # 假设前面设置为负数的数字的总和是 neg。那么设置为正数的数字的总和为 sum - neg
    # 那么 (sum - neg) - neg = target => neg = （sum - target）/ 2
    # 所以问题转为 0-1 背包问题：
    # 在数组 nums 列表中不可重复的选择数字组合，使得组合中所有数字之和为 neg
    # 求有多少组合数？
    def findTargetSumWays1(self, nums: List[int], target: int) -> int:
        diff = sum(nums) - target
        if diff < 0 or diff % 2 == 1:
            return 0

        neg = diff // 2
        dp = [0] * (neg + 1)
        dp[0] = 1
        for num in nums:
            for c in range(neg, num - 1, -1):
                dp[c] += dp[c - num]

        return dp[neg]

    # DFS（超时）
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        ans = 0

        def dfs(i, sum) -> None:
            nonlocal ans
            if i == len(nums):
                if sum == target: ans += 1
                return
            dfs(i + 1, sum + nums[i])
            dfs(i + 1, sum - nums[i])

        dfs(0, 0)
        return ans