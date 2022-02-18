from typing import List


def findDiagonalOrder(self, matrix: List[List[int]]) -> List[int]:
    m = len(matrix)
    if m == 0:
        return list()
    n = len(matrix[0])
    result = [0] * (m * n)
    row = col = d = 0
    dirs = [[-1, 1], [1, -1]]
    for i in range(m * n):
        result[i] = matrix[row][col]
        row += dirs[d][0]
        col += dirs[d][1]

        if row >= m: row, col, d = m - 1, col + 2, 1 - d
        if col >= n: row, col, d = row + 2, n - 1, 1 - d
        if row < 0: row, d = 0, 1 - d
        if col < 0: col, d = 0, 1 - d
    return result
