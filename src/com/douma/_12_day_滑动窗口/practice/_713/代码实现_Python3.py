from typing import List


class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        if k <= 1: return 0

        left = right = 0
        ans, prod = 0, 1
        while right < len(nums):
            prod *= nums[right]

            while prod >= k:
                prod //= nums[left]
                left += 1

            # [10,5,2,6]
            # 第一个窗口 [10]      --> 符合条件的子数组：1
            # 第二个窗口 [10, 5]   --> 符合条件的子数组：[10, 5] 和 [5] 即 2 个 = right - left + 1
            # ......
            ans += right - left + 1
            right += 1
        return ans