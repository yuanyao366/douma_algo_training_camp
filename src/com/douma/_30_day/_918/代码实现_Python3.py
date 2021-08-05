from typing import List


class Solution:

    # 数组是环形数组，意味着可以数组的首尾相连，头部和尾巴的元素是连续的
    # 那么，要求最大子数组和，包括了两种情况：
    # 1. 假设数组不是环形数组，这个时候只需要按照 leetcode 53 那样在数组内求最大子数组和
    # 2. 假设数组是环形数组，而且最大子数组和的子数组包含了首尾两个元素
    # 这种情况，我们只需要求出数组内最小子数组和，然后使用数组累加和减去最小子数组和即可
    # 最后，染回上面两种情况的最大值
    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        # 1. 在原数组 nums 内求最大子数组和，参考 leetcode 53 号算法题：最大子序和
        dp = [0] * len(nums)
        max_sum = dp[0] = nums[0]
        for i in range(1, len(nums)):
            dp[i] = max(dp[i - 1]  + nums[i], nums[i])
            max_sum = max(max_sum, dp[i])

        # 如果最子数组和小于 0，说明数组中全为负数，返回最大负数即可
        if max_sum < 0:
            return max_sum

        # 2. 在原数组 nums 内求最小子数组和
        dp = [0] * len(nums)
        min_sum = dp[0] = sum_ = nums[0]
        for i in range(1, len(nums)):
            dp[i] = min(dp[i - 1]  + nums[i], nums[i])
            min_sum = min(min_sum, dp[i])
            sum_ += nums[i]

        return max(max_sum, sum_ - min_sum)