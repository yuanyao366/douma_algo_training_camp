from typing import List


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        if len(nums) < 3:
            return []

        nums.sort()

        res = []
        for i in range(0, len(nums) - 2):
            if i > 0 and nums[i] == nums[i - 1]: continue
            target = -nums[i]
            left, right = i + 1, len(nums) - 1
            while left < right:
                s = nums[left] + nums[right]
                if s == target:
                    res.append([nums[i], nums[left], nums[right]])
                    # 去重
                    while left < right:
                        left = left + 1
                        if nums[left - 1] != nums[left]: break
                    while left < right:
                        right = right - 1
                        if nums[right + 1] != nums[right]: break
                elif s < target:
                    left = left + 1
                else:
                    right = right - 1
        return res