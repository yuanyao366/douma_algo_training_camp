from typing import List


class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        c_index = [-1] * 26
        for i in range(len(s)):
            c_index[ord(s[i]) - ord('a')] = i

        ans = []
        left = right = 0
        for i in range(len(s)):
            right = max(right, c_index[ord(s[i]) - ord('a')])
            if i == right:
                ans.append(right - left + 1)
                left = right + 1
        return ans