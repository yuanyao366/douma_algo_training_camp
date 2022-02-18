from typing import List


class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        n = len(nums)

        nums.sort()

        # 第 1 步：动态规划找出最大子集的个数、最大子集中的最大整数

        # 经典 LIS 问题
        # dp[i]：表示以 nums[i] 结尾最大整除子集的大小
        dp = [1] * n
        maxSize, maxVal = 1, dp[0]
        for i in range(1, n):
            for j in range(0, i):
                # 题目中说「没有重复元素」很重要
                if nums[i] % nums[j] == 0:
                    dp[i] = max(dp[i], dp[j] + 1)
            if dp[i] > maxSize:
                maxSize, maxVal = dp[i], nums[i]

        # 第 2 步：倒推获得最大子集
        res = []
        if maxSize == 1:
            res.append(nums[0])
            return res

        for i in range(n - 1, -1, -1):
            if maxSize > 0 and dp[i] == maxSize and maxVal % nums[i] == 0:
                res.append(nums[i])
                maxVal, maxSize = nums[i], maxSize - 1

        return res