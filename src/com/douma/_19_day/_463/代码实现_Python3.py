from collections import deque
from typing import List


class Solution:
    def __init__(self):
        self.grid = []
        self.rows = self.cols = self.res = 0
        self.visited = []
        self.dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    def in_area(self, row, col) -> bool:
        return 0 <= row < self.rows and 0 <= col < self.cols

    def islandPerimeter(self, grid: List[List[int]]) -> int:
        self.grid = grid
        self.rows, self.cols = len(grid), len(grid[0])
        self.visited = [[False] * self.cols for _ in range(self.rows)]

        for row in range(self.rows):
            for col in range(self.cols):
                if grid[row][col] == 1:
                    return self.bfs(row, col)

    # 1. 前序遍历
    def dfs1(self, row, col) -> None:
        if not self.in_area(row, col) or self.grid[row][col] == 0 or self.visited[row][col]:
            return
        self.visited[row][col] = True
        for d in self.dirs:
            next_row, next_col = row + d[0], col + d[1]
            if not self.in_area(next_row, next_col) or self.grid[next_row][next_col] == 0:
                self.res += 1
            self.dfs1(next_row, next_col)

    # 2. 后序遍历
    def dfs(self, row, col) -> int:
        if not self.in_area(row, col) or self.grid[row][col] == 0:
            return 1
        if self.visited[row][col]:
            return 0
        self.visited[row][col] = True
        res = 0
        for d in self.dirs:
            next_row, next_col = row + d[0], col + d[1]
            res += self.dfs(next_row, next_col)
        return res

    # 3. 广度优先遍历
    def bfs(self, row, col) -> int:
        res, queue = 0, deque()
        queue.append([row, col])
        self.visited[row][col] = True
        while len(queue):
            curr = queue.popleft()
            for d in self.dirs:
                next_row, next_col = curr[0] + d[0], curr[1] + d[1]
                if not self.in_area(next_row, next_col) or self.grid[next_row][next_col] == 0:
                    res += 1
                elif not self.visited[next_row][next_col]:
                    queue.append([next_row, next_col])
                    self.visited[next_row][next_col] = True
        return res