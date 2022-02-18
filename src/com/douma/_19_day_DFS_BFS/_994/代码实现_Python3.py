from collections import deque
from typing import List


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(grid), len(grid[0])

        level_map, queue = {}, deque()
        for row in range(rows):
            for col in range(cols):
                if grid[row][col] == 2:
                    index = row * cols + col
                    queue.append(index)
                    level_map[index] = 0

        ans = 0
        while len(queue):
            curr_index = queue.popleft()
            row, col = curr_index // cols, curr_index % cols

            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                if next_row >= 0 and next_row < rows and next_col >= 0 and next_col < cols and grid[next_row][next_col] == 1:
                    grid[next_row][next_col] = 2
                    index = next_row * cols + next_col
                    queue.append(index)
                    ans = level_map[curr_index] + 1
                    level_map[index] = ans

        for row in range(rows):
            for col in range(cols):
                if grid[row][col] == 1:
                    return -1

        return ans