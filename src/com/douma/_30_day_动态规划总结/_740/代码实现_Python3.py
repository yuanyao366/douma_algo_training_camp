from typing import List


class Solution:
    # 选择 nums[i] ，然后删除所有的 nums[i] - 1 和 nums[i] + 1
    # 这个意思就是，偷了 nums[i] 后，就不能偷 nums[i] 前后相邻的两个数了
    # 所以，问题可以抽象为打家劫舍问题

    # 算法步骤为：
    # 1. 先对 nums 中的每个元素进行个数统计，得到 sum 数组，
    # 存储在一个数组中，元素的值作为 sum 数组的下标，
    # 相同元素的累加和作为 sum 数组元素的值
    # 2. 然后对 sum 数组进行打家劫舍
    def deleteAndEarn(self, nums: List[int]) -> int:
        max_val = max(nums)
        sum_ = [0] * (max_val + 1)
        for val in nums:
            sum_[val] += val
        return self.rob(sum_)

    # 2. 动态规划
    def rob(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1: return nums[0]

        dp = [-1] * n

        dp[0] = nums[0]
        dp[1] = max(nums[0], nums[1])

        for i in range(2, n):
            dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])

        return dp[n - 1]