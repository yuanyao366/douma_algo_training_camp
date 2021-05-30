from typing import List


class Solution:
    # 二分查找
    # 时间复杂度：O(nlogn)
    # 空间复杂度：O(1)
    def twoSum1(self, numbers: List[int], target: int) -> List[int]:
        for i in range(len(numbers)):
            x = numbers[i]
            # 1. 二分查找 - O(logn)
            # [i + 1, nums.length - 1] 区间二分查找 target - x
            index = self.binary_search(numbers, i + 1, len(numbers) - 1, target - x)
            if index != -1:
                return [i + 1, index + 1]
        return []

    def binary_search(self, numbers: List[int], left: int, right: int, target: int) -> int:
        while left <= right:
            mid = left + (right - left) // 2
            if target == numbers[mid]:
                return mid
            elif target > numbers[mid]:
                left = mid + 1
            else:
                right = mid - 1
        return -1

    # 双指针
    # 时间复杂度：O(n)
    # 空间复杂度：O(1)
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        left, right = 0, len(numbers) - 1
        while left < right:
            sum = numbers[left] + numbers[right]
            if sum == target:
                return [left + 1, right + 1]
            elif sum < target:
                left = left + 1
            else:
                right = right - 1
        return []