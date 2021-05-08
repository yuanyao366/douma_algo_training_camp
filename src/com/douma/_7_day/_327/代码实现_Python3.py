from typing import List


class Solution:
    def countRangeSum(self, nums: List[int], lower: int, upper: int) -> int:
        prefixSum = [0] * (len(nums) + 1)
        for i in range(len(nums)):
            prefixSum[i + 1] = prefixSum[i] + nums[i]
        tmp = [0] * (len(nums) + 1)
        return self.merge_sort(prefixSum, lower, upper, 0, len(prefixSum) - 1, tmp)

    def merge_sort(self, prefixSum, lower, upper, lo, hi, tmp) -> int:
        if lo >= hi: return 0
        mid = lo + (hi - lo) // 2
        left = self.merge_sort(prefixSum, lower, upper, lo, mid, tmp)
        right = self.merge_sort(prefixSum, lower, upper, mid + 1, hi, tmp)

        count, i = 0, lo
        l = r = mid + 1
        while i <= mid:
            while l <= hi and prefixSum[l] - prefixSum[i] < lower: l += 1
            while r <= hi and prefixSum[r] - prefixSum[i] <= upper: r += 1
            count += (r - l)
            i += 1

        self.merge(prefixSum, lo, mid, hi, tmp)

        return left + right + count

    def merge(self, prefixSum, lo, mid, hi, tmp) -> None:
        for i in range(lo, hi + 1):
            tmp[i] = prefixSum[i]

        i, j = lo, mid + 1
        for k in range(lo, hi + 1):
            if i == mid + 1:
                prefixSum[k] = tmp[j]
                j = j + 1
            elif j == hi + 1:
                prefixSum[k] = tmp[i]
                i = i + 1
            elif tmp[i] <= tmp[j]:
                prefixSum[k] = tmp[i]
                i = i + 1
            else:
                prefixSum[k] = tmp[j]
                j = j + 1