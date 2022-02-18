class Solution:
    def reversePairs(self, nums: List[int]) -> int:
        tmp = [0] * len(nums)
        return self.merge_sort(nums, 0, len(nums) - 1, tmp)

    def merge_sort(self, nums, lo, hi, tmp) -> int:
        if lo >= hi: return 0
        mid = lo + (hi - lo) // 2
        left = self.merge_sort(nums, lo, mid, tmp)
        right = self.merge_sort(nums, mid + 1, hi, tmp)

        i, j, count = lo, mid + 1, 0
        while i <= mid:
            while j <= hi and nums[i] > 2 * nums[j]: j += 1
            count += (j - mid - 1)
            i += 1

        self.merge(nums, lo, mid, hi, tmp)

        return left + right + count

    def merge(self, nums, lo, mid, hi, tmp) -> None:
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