from typing import List


class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(board), len(board[0])
        visited = [[False] * cols for _ in range(rows)]

        def in_area(row, col) -> bool:
            return 0 <= row < rows and 0 <= col < cols

        def dfs(row, col) -> None:
            if not in_area(row, col) or board[row][col] == 'X' or visited[row][col]:
                return
            visited[row][col] = True
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                dfs(next_row, next_col)

        for col in range(cols):
            if board[0][col] == 'O' and not visited[0][col]:
                dfs(0, col)
            if board[rows - 1][col] == 'O' and not visited[rows - 1][col]:
                dfs(rows - 1, col)

        for row in range(1, rows - 1):
            if board[row][0] == 'O' and not visited[row][0]:
                dfs(row, 0)
            if board[row][cols - 1] == 'O' and not visited[row][cols - 1]:
                dfs(row, cols - 1)

        for row in range(1, rows - 1):
            for col in range(1, cols - 1):
                if board[row][col] == 'O' and not visited[row][col]:
                    board[row][col] = 'X'