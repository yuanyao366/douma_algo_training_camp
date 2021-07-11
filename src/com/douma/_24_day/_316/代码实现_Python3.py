from typing import List


class Solution:
    # 贪心 + 单调栈
    # 时间复杂度：O(n)
    # 空间复杂度：O(n)
    def removeDuplicateLetters(self, s: str) -> str:
        # 1. 计算字符在字符串 s 中的最后索引
        last_index = [-1] * 26
        for i in range(len(s)):
            last_index[ord(s[i]) - ord('a')] = i

        # 2. 维护单调栈
        stack, exists = [], [False] * 26
        for i in range(len(s)):
            if exists[ord(s[i]) - ord('a')]: continue
            while len(stack) and stack[-1] > s[i] and last_index[ord(stack[-1]) - ord('a')] > i:
                top = stack.pop()
                exists[ord(top) - ord('a')] = False
            stack.append(s[i])
            exists[ord(s[i]) - ord('a')] = True

        return ''.join(stack)