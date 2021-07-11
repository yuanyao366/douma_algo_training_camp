from typing import List


class Solution:
    # 贪心策略：贪心策略：每步都选择能跳到的最远距离
    def jump(self, nums: List[int]) -> int:
        if len(nums) == 1: return 0

        steps = start = end = max_pos = 0
        # 走到最后一个位置的时候就不用走了
        while end < len(nums) - 1:
            for i in range(start, end + 1):
                # 每次从 [start, end] 中都选择能跳到的最远距离
                max_pos = max(max_pos, i + nums[i])
            start = end + 1
            end, steps = max_pos, steps + 1

        return steps