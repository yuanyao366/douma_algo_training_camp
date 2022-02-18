from typing import List


class Solution:
    # 动态规划 (前缀和解法) -- 超出时间限制了
    # 时间复杂度：O(n^2)
    # 空间复杂度：O(n)
    def maxSubArray1(self, nums: List[int]) -> int:
        # 状态定义：prefixSum[i] 表示子数组 [0, j] 的累加和
        prefix_sum = [0] * len(nums)

        # 状态初始化
        prefix_sum[0] = max_sum = nums[0]
        for i in range(1, len(nums)):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i]
            max_sum = max(max_sum, prefix_sum[i])

        # 状态转移
        for i in range(1, len(nums)):
            for j in range(i, len(nums)):
                sum = prefix_sum[j] - prefix_sum[i - 1]
                max_sum = max(max_sum, sum)

        return max_sum

    # 动态规划(改变状态定义)
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def maxSubArray2(self, nums: List[int]) -> int:
        # 状态定义：dp[i] 表示以索引为 i 的元素结尾的最大子数组和
        dp = [0] * len(nums)

        # 状态初始化
        max_sum = dp[0] = nums[0]

        # 状态转移
        for i in range(1, len(nums)):
            dp[i] = max(dp[i - 1]  + nums[i], nums[i])
            max_sum = max(max_sum, dp[i])

        return max_sum

    # 动态规划(改变状态定义) + 状态压缩
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def maxSubArray(self, nums: List[int]) -> int:
        max_sum = pre_max_sum = nums[0]

        # 状态转移
        for i in range(1, len(nums)):
            pre_max_sum = max(pre_max_sum  + nums[i], nums[i])
            max_sum = max(max_sum, pre_max_sum)

        return max_sum