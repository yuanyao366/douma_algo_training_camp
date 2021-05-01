from typing import List


def runningSum(self, nums: List[int]) -> List[int]:
    prefix_sum = [nums[0]] * len(nums)
    for i in range(1, len(nums)):
        prefix_sum[i] = prefix_sum[i - 1] + nums[i]
    return prefix_sum
