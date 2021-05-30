from typing import List


class Solution:
    def maxProduct(self, words: List[str]) -> int:
        bitmask_map, ans = {}, 0
        for i in range(len(words)):
            bitmask = 0
            for c in words[i]:
                bitmask |= 1 << (ord(c) - ord('a'))
            if bitmask in bitmask_map:
                bitmask_map[bitmask] = max(bitmask_map[bitmask], len(words[i]))
            else:
                bitmask_map[bitmask] = len(words[i])

        for x in bitmask_map:
            for y in bitmask_map:
                if (x & y) == 0:
                    ans = max(ans, bitmask_map[x] * bitmask_map[y])

        return ans