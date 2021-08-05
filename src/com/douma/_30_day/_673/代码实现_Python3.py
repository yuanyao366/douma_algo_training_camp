from typing import List


class Solution:
    def findNumberOfLIS(self, nums: List[int]) -> int:
        n = len(nums)
        if n <= 1: return n
        # 1. 状态(双状态)数组的定义
        # lengths[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的长度
        # counts[i] 表示在子区间 [0...i] 且以 nums[i] 结尾的最长递增子序列的个数

        # 2. 状态初始化
        # 每个元素都可以以自身为一个长度的子序列，所以 lengths 初始化为 1
        # 因为每个元素可以以自身结尾的最长子序列的情况至少有一种，所以 counts 初始化为 1
        lengths, counts = [1] * n, [1] * n

        # 3. 状态转移
        for i in range(n):
            for j in range(i):
                # 需要符合递增条件
                if nums[i] > nums[j]:
                    # 以 nums[j] 结尾的最长递增序列长度 >= 以 nums[i] 结尾的最长递增序列长度
                    if lengths[j] >= lengths[i]:
                        lengths[i], counts[i] = lengths[j] + 1, counts[j]
                    elif lengths[j] + 1 == lengths[i]:
                        counts[i] += counts[j]

        # 求最长递增序列的长度
        longest = max(lengths)

        # 求最长递增序列的个数
        ans = 0
        for i in range(n):
            if lengths[i] == longest:
                ans += counts[i]

        return ans