from typing import List


class Solution:
    def longestMountain(self, arr: List[int]) -> int:
        n, ans, left = len(arr), 0, 0
        while left + 2 < n:
            right = left + 1
            if arr[left] < arr[right]:
                while right + 1 < n and arr[right] < arr[right + 1]:
                    right += 1
                if right + 1 < n and arr[right] > arr[right + 1]:
                    while right + 1 < n and arr[right] > arr[right + 1]:
                        right += 1
                    ans = max(ans, right - left + 1)
                else:
                    right += 1
            left = right
        return ans