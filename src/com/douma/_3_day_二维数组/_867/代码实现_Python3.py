from typing import List


def transpose(self, matrix: List[List[int]]) -> List[List[int]]:
    m, n = len(matrix), len(matrix[0])
    t = [[0] * m for i in range(n)]
    for i in range(m):
        for j in range(n):
            t[j][i] = matrix[i][j]

    return t