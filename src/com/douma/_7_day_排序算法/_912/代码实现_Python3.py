from typing import List
import random


class Solution:

    def merge_sort(self, nums, lo, hi, tmp) -> None:
        if lo >= hi: return
        mid = lo + (hi - lo) // 2
        self.merge_sort(nums, lo, mid, tmp)
        self.merge_sort(nums, mid + 1, hi, tmp)

        for i in range(lo, hi + 1):
            tmp[i] = nums[i]

        i, j = lo, mid + 1
        for k in range(lo, hi + 1):
            if i == mid + 1:
                nums[k] = tmp[j]
                j = j + 1
            elif j == hi + 1:
                nums[k] = tmp[i]
                i = i + 1
            elif tmp[i] <= tmp[j]:
                nums[k] = tmp[i]
                i = i + 1
            else:
                nums[k] = tmp[j]
                j = j + 1

    # 归并排序
    def sortArray(self, nums: List[int]) -> List[int]:
        tmp = [0] * len(nums)
        self.merge_sort(nums, 0, len(nums) - 1, tmp)
        return nums

    def partition(self, nums, lo, hi) -> None:
        i = random.randint(lo, hi)
        nums[i], nums[hi] = nums[hi], nums[i]
        pivot = nums[hi]
        less = great = lo
        while great <= hi - 1:
            if nums[great] < pivot:
                nums[less], nums[great] = nums[great], nums[less]
                less += 1
            great += 1
        nums[less], nums[hi] = nums[hi], nums[less]
        return less

    def quick_sort(self, nums, lo, hi) -> None:
        if lo >= hi: return
        index = self.partition(nums, lo, hi)
        self.quick_sort(nums, lo, index - 1)
        self.quick_sort(nums, index + 1, hi)

    # 快速排序
    def sortArray1(self, nums: List[int]) -> List[int]:
        self.quick_sort(nums, 0, len(nums) - 1);
        return nums

    # 2. 快速排序 - 三路快排
    def sortArray(self, nums: List[int]) -> List[int]:

        def quick_sort(nums, lo, hi) -> None:
            if lo >= hi: return
            i = random.randint(lo, hi)
            nums[i], nums[hi] = nums[hi], nums[i]
            pivot, less, great = nums[hi], lo, hi
            i = lo
            while i <= great:
                if nums[i] < pivot:
                    nums[i], nums[less] = nums[less], nums[i]
                    less, i = less + 1, i + 1
                elif nums[i] > pivot:
                    nums[i], nums[great] = nums[great], nums[i]
                    great -= 1
                else:
                    i += 1
            quick_sort(nums, lo, less - 1)
            quick_sort(nums, great + 1, hi)

        quick_sort(nums, 0, len(nums) - 1)
        return nums