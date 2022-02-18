from typing import List


def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
    m, n = len(matrix), len(matrix[0])

    dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    row = col = di = 0

    res = list()
    seen = [[False] * n for _ in range(m)]

    for i in range(m * n):
        res.append(matrix[row][col])
        seen[row][col] = True

        next_row = row + dirs[di][0]
        next_col = col + dirs[di][1]

        if not (0 <= next_row < m and 0 <= next_col < n and not seen[next_row][next_col]):
            di = (di + 1) % 4

        row = row + dirs[di][0]
        col = col + dirs[di][1]

    return res


# 法二：按层模拟
def spiralOrder1(self, matrix: List[List[int]]) -> List[int]:
    start_row, end_row = 0, len(matrix) - 1
    start_col, end_col = 0, len(matrix[0]) - 1

    res = list()
    while start_row <= end_row and start_col <= end_col:
        # top 行
        for col in range(start_col, end_col + 1): res.append(matrix[start_row][col])
        # right 列
        for row in range(start_row + 1, end_row + 1): res.append(matrix[row][end_col])
        if start_row < end_row and start_col < end_col:
            # bottom 行
            for col in range(end_col - 1, start_col, -1): res.append(matrix[end_row][col])
            # left 列
            for row in range(end_row, start_row, -1): res.append(matrix[row][start_col])
        start_row += 1
        end_row -= 1
        start_col += 1
        end_col -= 1

    return res
