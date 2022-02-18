from typing import List


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1, 1]]
        rows, cols = len(board), len(board[0])
        visited = [[False] * cols for _ in range(rows)]

        def in_area(row, col) -> bool:
            return 0 <= row < rows and 0 <= col < cols

        def dfs(row, col) -> None:
            if not in_area(row, col) or board[row][col] != 'E' or visited[row][col]:
                return
            visited[row][col] = True
            mines = 0
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                if in_area(next_row, next_col) and board[next_row][next_col] == 'M':
                    mines += 1

            if mines > 0:
                board[row][col] = str(mines)
            else:
                board[row][col] = 'B'
                for d in dirs:
                    next_row, next_col = row + d[0], col + d[1]
                    dfs(next_row, next_col)

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
        else:
            dfs(click[0], click[1])
        return board