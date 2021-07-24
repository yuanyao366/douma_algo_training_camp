from typing import List


class Solution:
    def rob(self, nums: List[int]) -> int:

        def rob(start: int, end: int) -> int:
            n = len(nums)
            if n == 1: return nums[0]

            prev_max = curr_max = 0

            for i in range(start, end + 1):
                tmp_max = max(curr_max, prev_max + nums[i])
                prev_max, curr_max = curr_max, tmp_max

            return curr_max

        n = len(nums)
        if n == 1: return nums[0]
        not_rob_first_house = rob(1, n - 1)
        not_rob_last_house = rob(0, n - 2)
        return max(not_rob_first_house, not_rob_last_house)