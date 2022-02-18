from typing import List


class Solution:
    def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
        n, cnt, res = len(nums), [0] * 101, [0] * len(nums)
        for num in nums: cnt[num] += 1
        for i in range(1, 101): cnt[i] += cnt[i - 1]
        for i in range(n):
            res[i] = cnt[nums[i] - 1] if nums[i] != 0 else 0
        return res