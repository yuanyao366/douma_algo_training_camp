from typing import List


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(grid), len(grid[0])
        visited = [[False] * cols for _ in range(rows)]

        def in_area(row, col) -> bool:
            return 0 <= row < rows and 0 <= col < cols

        def dfs(row, col) -> None:
            if not in_area(row, col) or grid[row][col] == '0' or visited[row][col]:
                return
            visited[row][col] = True
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                dfs(next_row, next_col)

        ans = 0
        for row in range(rows):
            for col in range(cols):
                if grid[row][col] == '1' and not visited[row][col]:
                    dfs(row, col)
                    ans += 1

        return ans