from typing import List


class Solution:
    # 1. 记忆化搜索(一)
    def rob1(self, nums: List[int]) -> int:
        n = len(nums)
        memo = [-1] * n

        def dfs(i) -> int:
            if i >= n: return 0
            if memo[i] != -1: return memo[i]
            left = dfs(i + 1)
            right = dfs(i + 2)
            memo[i] = max(left, right + nums[i])
            return memo[i]

        return dfs(0)

    # 2. 记忆化搜索(二)
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        memo = [-1] * n

        def dfs(i) -> int:
            if i == 0: return nums[0]
            if i == 1: return max(nums[0], nums[1])
            if memo[i] != -1: return memo[i]
            left = dfs(i - 1)
            right = dfs(i - 2)
            memo[i] = max(left, right + nums[i])
            return memo[i]

        return dfs(len(nums) - 1)

    # 3. 动态规划
    def rob3(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1: return nums[0]

        dp = [-1] * n

        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for i in range(2, n):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])

        return dp[n - 1]

    # 4. 动态规划 + 状态更新
    def rob4(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1: return nums[0]

        prev_max = curr_max = 0

        for num in nums:
            tmp_max = max(curr_max, prev_max + num)
            prev_max, curr_max = curr_max, tmp_max

        return curr_max