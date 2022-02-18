class Solution:
    def monotoneIncreasingDigits(self, n: int) -> int:
        nums = [int(i) for i in str(n)]
        i = 1
        while i < len(nums) and nums[i - 1] <= nums[i]:
            i += 1
        if i < len(nums):
            while i > 0 and nums[i - 1] > nums[i]:
                nums[i - 1] -= 1
                i -= 1
            i += 1
            while i < len(nums):
                nums[i] = 9
                i += 1
        res = 0
        for i in nums:
            res = res * 10 + i
        return res