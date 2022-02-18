from typing import List


class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        ans = left = right = 0
        while right < len(nums):
            if nums[right] == 0:
                ans = max(ans, right - left)
                left = right + 1
            right += 1
        return max(ans, right - left)