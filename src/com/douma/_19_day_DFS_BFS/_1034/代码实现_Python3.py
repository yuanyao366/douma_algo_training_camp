from typing import List


class Solution:
    def colorBorder(self, grid: List[List[int]], r0: int, c0: int, color: int) -> List[List[int]]:
        curr_color = grid[r0][c0]
        if curr_color == color:
            return grid

        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(grid), len(grid[0])
        visited = [[False] * cols for _ in range(rows)]

        def in_area(row, col) -> bool:
            return 0 <= row < rows and 0 <= col < cols

        def dfs(row, col) -> None:
            visited[row][col] = True
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                if not in_area(next_row, next_col) or (grid[next_row][next_col] != curr_color and not visited[next_row][next_col]):
                    grid[row][col] = color
                elif not visited[next_row][next_col]:
                    dfs(next_row, next_col)

        dfs(r0, c0)
        return grid