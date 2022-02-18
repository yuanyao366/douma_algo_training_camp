from typing import List


class Solution:

    def countSmaller(self, nums: List[int]) -> List[int]:
        n = len(nums)
        self.tmp = [0] * n
        self.tmp_indexes = [0] * n
        self.ans = [0] * n
        self.indexes = [i for i in range(n)]

        self.merge_sort(nums, 0, n - 1)

        return self.ans

    def merge_sort(self, nums, lo, hi) -> None:
        if lo >= hi: return
        mid = lo + (hi - lo) // 2
        self.merge_sort(nums, lo, mid)
        self.merge_sort(nums, mid + 1, hi)

        for i in range(lo, hi + 1):
            self.tmp[i] = nums[i]
            self.tmp_indexes[i] = self.indexes[i]

        i, j = lo, mid + 1
        for k in range(lo, hi + 1):
            if i == mid + 1:
                nums[k], self.indexes[k] = self.tmp[j], self.tmp_indexes[j]
                j = j + 1
            elif j == hi + 1:
                nums[k], self.indexes[k] = self.tmp[i], self.tmp_indexes[i]
                self.ans[self.tmp_indexes[i]] += (j - mid - 1)
                i = i + 1
            elif self.tmp[i] <= self.tmp[j]:
                nums[k], self.indexes[k] = self.tmp[i], self.tmp_indexes[i]
                self.ans[self.tmp_indexes[i]] += (j - mid - 1)
                i = i + 1
            else:
                nums[k], self.indexes[k] = self.tmp[j], self.tmp_indexes[j]
                j = j + 1