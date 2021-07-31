from typing import List


class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:
        m, n = len(nums1), len(nums2)

        # dp[i][j]：表示 A 中前 i 个元素中和 B 的前 j 个元素中最长公共子数组长度
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        ans = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = dp[i - 1][j - 1] + 1 if nums1[i - 1] == nums2[j - 1] else 0
                ans = max(ans, dp[i][j])

        return ans