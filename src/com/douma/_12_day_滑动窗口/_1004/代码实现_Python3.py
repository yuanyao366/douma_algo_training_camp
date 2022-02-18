from typing import List


class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        ans = left = right = one_counts = 0
        while right < len(nums):
            if nums[right] == 1:
                one_counts += 1

            while (right - left + 1) - one_counts > k:
                if nums[left] == 1:
                    one_counts -= 1
                left += 1

            ans = max(ans, right - left + 1)
            right += 1
        return ans