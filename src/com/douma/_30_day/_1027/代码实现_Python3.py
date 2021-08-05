from typing import List


class Solution:
    def longestArithSeqLength(self, nums: List[int]) -> int:
        n, ans = len(nums), 0

        # dp[i][j]：表示以 nums[i] 为结尾且公差为 j 的最长等差数列的长度
        dp = [[1] * 20010 for _ in range(n)]

        for i in range(n):
            for j in range(i):
                diff = nums[i] - nums[j]
                # +10000 的目的：保证公差 diff 为正数
                diff += 10000
                dp[i][diff] = max(dp[i][diff], dp[j][diff] + 1)
                ans = max(dp[i][diff], ans)

        return ans