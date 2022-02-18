from typing import List


class Solution:
    def minSwaps(self, data: List[int]) -> int:
        INT_MAX = 2 ** 31 - 1
        k = sum(e for e in data if e == 1)
        left = right = 0
        window_zero_count, min_zero_count = 0, INT_MAX
        while right < len(data):
            if data[right] == 0:
                window_zero_count += 1
            if right - left + 1 == k:
                min_zero_count = min(min_zero_count, window_zero_count)
                if data[left] == 0:
                    window_zero_count -= 1
                left += 1
            right += 1

        return 0 if min_zero_count == INT_MAX else min_zero_count