from typing import List


class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if len(nums) == 0: return [-1, -1]
        first = self.searchFirstIndex(nums, target)
        if first == -1: return [-1, -1]
        last = self.searchLastIndex(nums, target)
        return [first, last]

    def searchFirstIndex(self, nums: List[int], target) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                if mid == 0 or nums[mid - 1] != target: return mid
                else: right = mid - 1
            elif nums[mid] < target: left = mid + 1
            else: right = mid - 1
        return -1

    def searchLastIndex(self, nums: List[int], target) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                if mid == len(nums) - 1 or nums[mid + 1] != target: return mid
                else: left = mid + 1
            elif nums[mid] < target: left = mid + 1
            else: right = mid - 1
        return -1