from typing import List


class Solution:
    def majorityElement1(self, nums: List[int]) -> int:
        return self.majorityElementHelp(nums, 0, len(nums) - 1)

    def majorityElementHelp(self, nums: List[int], lo: int, hi: int) -> int:
        if lo == hi:
            return nums[lo]
        mid = lo + (hi - lo) // 2
        left_num = self.majorityElementHelp(nums, lo, mid)
        right_num = self.majorityElementHelp(nums, mid + 1, hi)

        if left_num == right_num:
            return left_num
        left_num_cnt = self.count_in_range(nums, left_num, lo, hi)
        right_num_cnt = self.count_in_range(nums, right_num, lo, hi)

        return left_num if left_num_cnt > right_num_cnt else right_num

    def count_in_range(self, nums: List[int], target: int, lo: int, hi: int) -> int:
        count = 0
        for i in range(lo, hi + 1):
            if target == nums[i]:
                count += 1
        return count

    # 摩尔投票算法
    def majorityElement(self, nums: List[int]) -> int:
        cadidate = count = 0
        for num in nums:
            if num == cadidate:
                count += 1
            elif count == 0:
                count, cadidate = count + 1, num
            else:
                count -= 1
        return cadidate