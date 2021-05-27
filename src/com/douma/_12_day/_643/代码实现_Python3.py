from typing import List


class Solution:
    # 滑动窗口
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        left = right = 0
        max_sum, window_sum = -2 ** 31, 0
        while right < len(nums):
            window_sum += nums[right]
            if right - left + 1 == k:
                max_sum = max(max_sum, window_sum)
                window_sum -= nums[left]
                left += 1
            right += 1
        return max_sum / k