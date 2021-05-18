from typing import List


class Solution:
    # 枚举高 + 单调栈优化 + 一次遍历
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        left, right = [0] * n, [n] * n
        stack = []
        for i in range(n):
            while stack and heights[i] <= heights[stack[-1]]:
                right[stack[-1]] = i
                stack.pop()
            left[i] = -1 if not stack else stack[-1]
            stack.append(i)

        ans = 0
        for mid in range(n):
            height, width = heights[mid], right[mid] - left[mid] - 1
            ans = max(ans, height * width)

        return ans