from typing import List


class Solution:
    # 动态规划
    def maxProduct1(self, nums: List[int]) -> int:
        length = len(nums)
        max_p, min_p = [0] * length, [0] * length
        max_p[0] = min_p[0] = ans = nums[0]
        for i in range(1, length):
            max_p[i] = max(max_p[i - 1] * nums[i], max(min_p[i - 1] * nums[i], nums[i]))
            min_p[i] = min(min_p[i - 1] * nums[i], min(max_p[i - 1] * nums[i], nums[i]))
            ans = max(ans, max_p[i])
        return ans

    # 动态规划 + 状态压缩
    def maxProduct(self, nums: List[int]) -> int:
        length = len(nums)
        max_p = min_p = ans = nums[0]
        for i in range(1, length):
            mx, mn = max_p, min_p
            max_p = max(mx * nums[i], max(mn * nums[i], nums[i]))
            min_p = min(mn * nums[i], min(mx * nums[i], nums[i]))
            ans = max(ans, max_p)
        return ans