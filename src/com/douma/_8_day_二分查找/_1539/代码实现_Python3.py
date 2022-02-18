from typing import List


class Solution:
    def findKthPositive(self, arr: List[int], k: int) -> int:
        if arr[0] > k: return k
        left, right = 0, len(arr)
        while left < right:
            mid = (left + right) // 2
            x = arr[mid] if mid < len(arr) else 10**9
            if (x - mid - 1 < k): left = mid + 1
            else: right = mid
        leftMissCount = arr[left - 1] - (left - 1) - 1
        return arr[left - 1] + (k - leftMissCount)