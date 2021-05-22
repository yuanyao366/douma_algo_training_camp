from typing import List
import heapq


class Solution:
    def lastStoneWeight1(self, stones: List[int]) -> int:
        n = len(stones)
        if n == 1: return stones[0]
        for i in range(0, n - 1):
            stones.sort(reverse=False)
            x, y = stones[n - 2], stones[n - 1]
            if x == 0: break
            stones[n - 1], stones[n - 2] = y - x, 0
        return stones[n - 1]

    def lastStoneWeight(self, stones: List[int]) -> int:
        n = len(stones)
        if n == 1: return stones[0]
        s = [-x for x in stones]
        heapq.heapify(s)
        while len(s) > 1:
            y = heapq.heappop(s)
            x = heapq.heappop(s)
            if x != y: heapq.heappush(s, y - x)
        return -s[0] if s else 0