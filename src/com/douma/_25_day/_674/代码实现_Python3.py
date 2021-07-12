from typing import List


class Solution:
    def findLengthOfLCIS(self, nums: List[int]) -> int:
        ans, slow = 1, 0
        for fast in range(1, len(nums)):
            if nums[fast - 1] >= nums[fast]:
                slow = fast
            ans = max(ans, fast - slow + 1)
        return ans