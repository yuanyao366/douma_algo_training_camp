class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        ans = left = right = window_cost = 0
        while right < len(s):
            window_cost += abs(ord(s[right]) - ord(t[right]))
            while window_cost > maxCost:
                window_cost -= abs(ord(s[left]) - ord(t[left]))
                left += 1
            ans = max(ans, right - left + 1)
            right += 1
        return ans