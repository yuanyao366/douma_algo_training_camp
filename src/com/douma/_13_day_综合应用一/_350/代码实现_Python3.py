from typing import List


class Solution:
    # 哈希查找
    # 时间复杂度：O(m + n)
    # 空间复杂度：O(m + n)
    def intersect1(self, nums1: List[int], nums2: List[int]) -> List[int]:
        count_map = {num:0 for num in nums1}
        for num in nums1:
            count_map[num] += 1

        res = []
        for num in nums2:
            if num in count_map and count_map.get(num) > 0:
                res.append(num)
                count_map[num] = count_map.get(num) - 1

        return res

    # 二分查找
    # 时间复杂度：O(mlogn + n)
    # 空间复杂度：O(m + n)
    def intersect2(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        res, i = [], 0
        while i < len(nums1):
            lower_bound = self.search_first_target(nums2, nums1[i])
            if lower_bound == -1:
                i += 1
                continue
            count = 0
            while lower_bound < len(nums2) and nums2[lower_bound] == nums1[i]:
                count, lower_bound = count + 1, lower_bound + 1
            j = i
            while j < len(nums1) and nums1[j] == nums1[i]:
                j += 1
                if count > 0:
                    res.append(nums1[i])
                    count -= 1
            i = j
        return res

    def search_first_target(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = left + (right - left) // 2
            if nums[mid] < target:
                left = mid + 1
            else:
                right = mid
        return -1 if nums[left] != target else left

    # 排序查找
    # 时间复杂度：O(m + n)
    # 空间复杂度：O(m + n)
    def intersect(self, nums1: List[int], nums2: List[int]) -> List[int]:
        nums1.sort()
        nums2.sort()
        res = []
        i = j = 0
        while i < len(nums1) and j < len(nums2):
            if nums1[i] == nums2[j]:
                res.append(nums1[i])
                i, j = i + 1, j + 1
            elif nums1[i] < nums2[j]:
                i += 1
            else:
                j += 1
        return res