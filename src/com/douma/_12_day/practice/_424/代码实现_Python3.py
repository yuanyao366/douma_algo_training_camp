class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        if len(s) < 2:
            return len(s)

        res = left = right = 0
        freq, max_count = [0] * 26, 0
        while right < len(s):
            freq[ord(s[right]) - ord('A')] += 1
            max_count = max(max_count, freq[ord(s[right]) - ord('A')])
            # 出现最多的次数，再加上 k 都没有当前窗口长度大，说明可以缩减窗口了
            if (right - left + 1) - max_count > k:
                freq[ord(s[left]) - ord('A')] -= 1
                left += 1
            res = max(res, right - left + 1)

            right += 1
        return res