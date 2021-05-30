from typing import List


class Solution:
    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        if len(nums) < 4:
            return []

        nums.sort()
        res = []
        for i in range(len(nums) - 3):
            # 忽略后面与前面重复的元素
            if i > 0 and nums[i] == nums[i - 1]: continue
            for j in range(i + 1, len(nums) - 2):
                # 忽略后面与前面重复的元素
                if j > i + 1 and nums[j] == nums[j - 1]: continue
                part_sum = nums[i] + nums[j]
                left, right = j + 1, len(nums) - 1
                while left < right:
                    s = part_sum + nums[left] + nums[right]
                    if s == target:
                        res.append([nums[i], nums[j], nums[left], nums[right]])
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