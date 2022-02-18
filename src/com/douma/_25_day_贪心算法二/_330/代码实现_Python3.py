from typing import List


class Solution:
    def minPatches(self, nums: List[int], n: int) -> int:
        res, x, index = 0, 1, 0
        while x <= n:
            if index < len(nums) and nums[index] <= x:
                x += nums[index]
                index += 1
            else:
                x, res = x * 2, res + 1
        return res