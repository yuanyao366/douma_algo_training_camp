from typing import List


class Solution:
    def dailyTemperatures(self, T: List[int]) -> List[int]:
        n = len(T)
        res = [0] * n
        # 数组实现栈的功能，数组的最后作为栈顶
        stack = []
        for i in range(n):
            while stack and T[i] > T[stack[-1]]:
                prev_index = stack.pop()
                res[prev_index] = i - prev_index
            stack.append(i)
        return res