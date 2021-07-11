from typing import List


class Solution:
    # 贪心策略：每次把最小的饼干分配给胃口最小的小孩，这样才能满足最多的小孩
    # g[i] 表示第 i 个小孩的胃口值
    # s[i] 表示第 i 个饼干的尺寸
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()

        i = j = 0
        while i < len(g) and j < len(s):
            if s[j] >= g[i]:
                i += 1
            j += 1

        return i