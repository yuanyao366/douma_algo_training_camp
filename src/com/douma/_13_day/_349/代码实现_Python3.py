from typing import List


class Solution:
    # 线性查找
    # 时间复杂度：O(m*n*(min(m, n)))
    # 空间复杂度：min(m, n)
    def intersection1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        res = []
        for num1 in nums1:
            for num2 in nums2:
                if num1 == num2:
                    if not num1 in res:
                        res.append(num1)
        return res

    # 线性查找
    # 时间复杂度：O(m*n)
    # 空间复杂度：min(m, n)
    def intersection2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        res = set()
        for num1 in nums1:
            for num2 in nums2:
                if num1 == num2:
                    res.add(num1)
        return list(res)

    # 二分查找
    # 时间复杂度：O((m + n)logn)
    # 空间复杂度：min(m, n)
    def intersection3(self, nums1: List[int], nums2: List[int]) -> List[int]:
        res = set()
        nums2.sort()
        for num1 in nums1:
            if self.contains(nums2, num1):
                res.add(num1)
        return list(res)

    def contains(self, nums: List[int], target: int) -> bool:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if target == nums[mid]:
                return True
            elif target < nums[mid]:
                right = mid - 1
            else:
                left = mid + 1
        return False

    # 哈希查找
    # 时间复杂度：O(m*n)
    # 空间复杂度：min(m, n)
    def intersection4(self, nums1: List[int], nums2: List[int]) -> List[int]:
        set1 = set()
        set2 = set(nums2)
        for num1 in nums1:
            if num1 in set2:
                set1.add(num1)
        return list(set1)

    # 排序查找
    # 时间复杂度：O(m*n)
    # 空间复杂度：min(m, n)
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        res = []
        nums1.sort()
        nums2.sort()
        i = j = 0
        while i < len(nums1) and j < len(nums2):
            if nums1[i] == nums2[j]:
                if len(res) == 0 or res[-1] != nums1[i]:
                    res.append(nums1[i])
                i, j = i + 1, j + 1
            elif nums1[i] < nums2[j]:
                i += 1
            else:
                j += 1
        return res