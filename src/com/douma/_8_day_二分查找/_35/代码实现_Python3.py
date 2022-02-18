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
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid

        return left

    # 思路一：在循环体内查找目标值
    def searchInsert1(self, nums: List[int], target: int) -> int:
        if len(nums) == 0: return 0
        n = len(nums)
        if target > nums[n - 1]:
            return len(nums)
        # 二分查找第一个大于等于 target 的索引
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if target <= nums[mid]:
                if mid == 0 or nums[mid - 1] < target:
                    return mid
                else:
                    right = mid - 1
            else:
                # 下一次搜索区间：[mid + 1...right]
                left = mid + 1
        return -1