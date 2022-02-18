from typing import List


def setZeroes(self, matrix: List[List[int]]) -> None:
    """
    Do not return anything, modify matrix in-place instead.
    """
    m, n = len(matrix), len(matrix[0])
    flag_col1 = False

    for row in range(m):
        if matrix[row][0] == 0: flag_col1 = True
        for col in range(1, n):
            if matrix[row][col] == 0: matrix[row][0] = matrix[0][col] = 0

    for row in range(m - 1, -1, -1):
        for col in range(1, n):
            if matrix[row][0] == 0 or matrix[0][col] == 0:
                matrix[row][col] = 0
        if flag_col1:
            matrix[row][0] = 0
