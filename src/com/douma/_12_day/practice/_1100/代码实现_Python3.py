class Solution:
    def numKLenSubstrNoRepeats(self, s: str, k: int) -> int:
        if len(s) < k: return 0
        left = right = 0
        ans, count = 0, [0] * 26
        while right < len(s):
            right_index = ord(s[right]) - ord('a')
            count[right_index] += 1
            while count[right_index] > 1:
                count[ord(s[left]) - ord('a')] -= 1
                left += 1
            if right - left + 1 == k:
                ans += 1
                count[ord(s[left]) - ord('a')] -= 1
                left += 1
            right += 1
        return ans