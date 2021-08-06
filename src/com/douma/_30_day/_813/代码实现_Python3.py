from typing import List


class Solution:
    def largestSumOfAverages(self, nums: List[int], k: int) -> float:
        n = len(nums)

        prefix_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1]

        # dp[i][j]: 表示将 nums 的前 i 个元素分割成 j 份得到的最大平均分数
        dp = [[0.0] * (k + 1) for _ in range(n + 1)]

        for i in range(1, n + 1):
            # 将 nums 的前 i 个元素分割成 1 份得到的最大分数
            dp[i][1] = prefix_sum[i] / i
            for j in range(2, min(k, i) + 1):
                # 将 nums 的前 i 个元素分割成 j 份得到的最大平均分数等于：
                # 所有将前 l 个字符分割成 j - 1 份得到的最大平均分数，再加上 [l...i] 的平均值，求最大值
                for l in range(1, i):
                    dp[i][j] = max(dp[i][j], dp[l][j - 1] + (prefix_sum[i] - prefix_sum[l]) / (i - l))

        return dp[n][k]