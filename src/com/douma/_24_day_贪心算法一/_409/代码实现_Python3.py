class Solution:
    def longestPalindrome(self, s: str) -> int:
        count, ans = [0] * 128, 0
        for c in s:
            count[ord(c)] += 1
        for v in count:
            ans += v // 2 * 2
            if v % 2 == 1 and ans % 2 == 0:
                ans += 1
        return ans