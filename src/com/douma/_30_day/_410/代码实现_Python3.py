from typing import List


class Solution:
    # 动态规划 (会超时)
    def splitArray1(self, nums: List[int], m: int) -> int:
        n = len(nums)

        prefix_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            prefix_sum[i] = prefix_sum[i - 1] + nums[i - 1]

        # dp[i][j]: 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值
        dp = [[2**31 - 1] * (m + 1) for _ in range(n + 1)]

        dp[0][0] = 0

        for i in range(1, n + 1):
            for j in range(1, min(m, i) + 1):
                for l in range(0, i):
                    dp[i][j] = min(dp[i][j], max(dp[l][j - 1], prefix_sum[i] - prefix_sum[l]))

        return dp[n][m]

    # 贪心
    def splitArray2(self, nums: List[int], m: int) -> int:
        def check(x: int) -> bool:
            total, cnt = 0, 1
            for num in nums:
                if total + num > x:
                    cnt += 1
                    total = num
                else:
                    total += num
            return cnt <= m


        left = max(nums)
        right = sum(nums)
        while left < right:
            mid = (left + right) // 2
            if check(mid):
                right = mid
            else:
                left = mid + 1

        return left