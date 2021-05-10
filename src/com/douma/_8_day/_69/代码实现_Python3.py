class Solution:
    def mySqrt(self, x: int) -> int:
        left, right, ans = 0, x, -1
        while left <= right:
            mid = (left + right) // 2
            if mid * mid <= x:
                ans, left = mid, mid + 1
            else:
                right = mid - 1
        return ans