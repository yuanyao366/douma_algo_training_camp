from typing import List


def rotate(self, matrix: List[List[int]]) -> None:
    """
    Do not return anything, modify matrix in-place instead.
    """
    n = len(matrix)
    for row in range(0, n // 2):
        for col in range(0, (n + 1) // 2):
            temp = matrix[row][col]
            matrix[row][col] = matrix[n - col - 1][row]
            matrix[n - col - 1][row] = matrix[n - row - 1][n - col - 1]
            matrix[n - row - 1][n - col - 1] = matrix[col][n - row - 1]
            matrix[col][n - row - 1] = temp