from collections import deque


class Solution:
    # 贪心 + 双端队列
    def removeKdigits(self, num: str, k: int) -> str:
        d = deque()
        for c in num:
            while len(d) and k > 0 and d[-1] > c:
                d.pop()
                k -= 1
            d.append(c)

        for i in range(k):
            d.pop()

        res, is_first = "", True
        while len(d):
            c = d.popleft()
            if c == '0' and is_first:
                continue
            res, is_first = res + c, False

        return "0" if len(res) == 0 else res