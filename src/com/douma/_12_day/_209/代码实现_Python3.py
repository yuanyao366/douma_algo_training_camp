from typing import List


class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        INT_MAX = 2 ** 31 - 1
        left = right = 0
        ans, windowSum = INT_MAX, 0
        while right < len(nums):
            windowSum += nums[right]
            while windowSum >= target:
                ans = min(ans, right - left + 1)
                windowSum -= nums[left]
                left += 1
            right += 1
        return 0 if ans == INT_MAX else ans