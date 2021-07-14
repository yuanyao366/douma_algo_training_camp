from typing import List


class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        m = len(nums)
        if m == 0: return 0

        # 状态：dp[i] 表示以 i 对应元素结尾的时候最长递增子序列的长度
        dp = [1] * m

        res = 1
        for j in range(1, m):
            for i in range(0, j):
                if nums[j] > nums[i]:
                    dp[j] = max(dp[i] + 1, dp[j])
                    res = max(res, dp[j])

        return res