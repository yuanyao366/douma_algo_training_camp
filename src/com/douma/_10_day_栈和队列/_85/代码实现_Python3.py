from typing import List


class Solution:
    def maximalRectangle(self, matrix: List[List[str]]) -> int:
        m = len(matrix)
        if m == 0: return 0
        n = len(matrix[0])

        # 计算每个元素左边连续 1 的个数(包括元素本身)
        left = [[0] * n for _ in range(m)]

        for row in range(m):
            for col in range(n):
                if matrix[row][col] == '1':
                    left_ones = 0 if col == 0 else left[row][col - 1]
                    left[row][col] = left_ones + 1

        ans = 0
        for col in range(n):
            up = [0] * m
            down = [m] * m
            stack = []
            for row in range(m):
                while stack and left[row][col] <= left[stack[-1]][col]:
                    down[stack[-1]] = row
                    stack.pop()
                up[row] = -1 if not stack else stack[-1]
                stack.append(row)

            for row in range(m):
                height, width = left[row][col], down[row] - up[row] - 1
                ans = max(ans, height * width)


        return ans