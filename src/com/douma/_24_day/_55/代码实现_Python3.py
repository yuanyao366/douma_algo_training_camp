from typing import List


class Solution:
    # 贪心策略：每步都选择能跳到的最远距离
    def canJump(self, nums: List[int]) -> bool:
        max_pos = 0
        for i in range(len(nums)):
            if i > max_pos:
                return False
            max_pos = max(max_pos, i + nums[i])
        return True