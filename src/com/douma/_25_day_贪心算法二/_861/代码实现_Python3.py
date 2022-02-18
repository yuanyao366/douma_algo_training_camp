from typing import List


class Solution:
    def matrixScore(self, grid: List[List[int]]) -> int:
        rows, cols = len(grid), len(grid[0])
        for row in range(rows):
            if grid[row][0] == 0:
                for col in range(cols):
                    grid[row][col] ^= 1

        res = 0
        for col in range(cols):
            cnt = 0
            for row in range(rows):
                cnt += grid[row][col]
            max_one_cnt = max(cnt, rows - cnt)
            res += max_one_cnt * (1 << (cols - col - 1))

        return res