from collections import deque
from typing import List


class Solution:
    # DFS
    def floodFill1(self, image: List[List[int]], sr: int, sc: int, newColor: int) -> List[List[int]]:
        old_color = image[sr][sc]
        if old_color == newColor:
            return image

        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(image), len(image[0])

        def dfs(row, col) -> None:
            if row < 0 or row >= rows or col < 0 or col >= cols or image[row][col] != old_color:
                return
            image[row][col] = newColor
            for d in dirs:
                next_row, next_col = row + d[0], col + d[1]
                dfs(next_row, next_col)

        dfs(sr, sc)
        return image

    # BFS
    def floodFill(self, image: List[List[int]], sr: int, sc: int, newColor: int) -> List[List[int]]:
        old_color = image[sr][sc]
        if old_color == newColor:
            return image
        dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        rows, cols = len(image), len(image[0])

        queue = deque()
        queue.append([sr, sc])
        image[sr][sc] = newColor

        while len(queue):
            curr = queue.popleft()
            for d in dirs:
                next_row, next_col = curr[0] + d[0], curr[1] + d[1]
                if (next_row >= 0 and next_row < rows and next_col >= 0 and next_col < cols
                        and image[next_row][next_col] == old_color):
                    queue.append([next_row, next_col])
                    image[next_row][next_col] = newColor

        return image