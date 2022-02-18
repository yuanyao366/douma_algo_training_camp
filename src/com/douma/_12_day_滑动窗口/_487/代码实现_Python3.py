from typing import List


class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = left = right = 0
        zero_index = -1
        while right < len(nums):
            if nums[right] == 0:
                if zero_index >= 0:
                    ans = max(ans, right - left)
                    left = zero_index + 1
                zero_index = right
            right += 1
        return max(ans, right - left)