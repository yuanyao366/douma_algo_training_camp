from typing import List


class Solution:
    # 动态规划
    def wiggleMaxLength1(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2: return n

        up, down = [0] * n, [0] * n
        up[0] = down[0] = 1

        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                up[i] = down[i - 1] + 1
                down[i] = down[i - 1]
            elif nums[i] < nums[i - 1]:
                up[i] = up[i - 1]
                down[i] = up[i] + 1
            else:
                up[i] = up[i - 1]
                down[i] = down[i - 1]
        return max(up[n - 1], down[n - 1])

    # 动态规划 + 状态压缩
    def wiggleMaxLength(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 2: return n

        up = down = 1
        for i in range(1, n):
            if nums[i] > nums[i - 1]:
                up = down + 1
            elif nums[i] < nums[i - 1]:
                down = up + 1

        return max(up, down)