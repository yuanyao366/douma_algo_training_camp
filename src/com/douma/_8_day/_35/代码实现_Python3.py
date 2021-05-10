from typing import List


class Solution:
    # 思路二：在循环体内排除没有目标值的区间
    def searchInsert(self, nums: List[int], target: int) -> int:
        if len(nums) == 0: return 0
        n = len(nums)
        if target <= nums[0]: return 0
        if target > nums[n - 1]: return n

        left, right = 0, n - 1
        while left < right:
            mid = (left + right) // 2
            if nums[mid] == target: return mid
            elif nums[mid] < target: left = mid + 1
            else: right = mid

        return left