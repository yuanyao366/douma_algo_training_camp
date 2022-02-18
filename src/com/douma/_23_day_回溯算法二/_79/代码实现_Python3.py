from typing import List



class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:

        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(board), len(board[0])
        visited = [[False] * cols for _ in range(rows)]

        def dfs(row, col, index) -> bool:
            if board[row][col] != word[index]:
                return False
            elif index == len(word) - 1:
                return True

            visited[row][col] = True
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                if 0 <= next_row < rows and 0 <= next_col < cols and not visited[next_row][next_col]:
                    if dfs(next_row, next_col, index + 1):
                        return True
            visited[row][col] = False
            return False

        for row in range(rows):
            for col in range(cols):
                if board[row][col] == word[0]:
                    if dfs(row, col, 0):
                        return True
        return False