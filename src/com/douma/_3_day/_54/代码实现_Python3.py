def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
    m, n = len(matrix), len(matrix[0])

    dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    row = col = di = 0

    res = list()
    seen = [[False] * n for _ in range(m)]

    for i in range(m * n):
        res.append(matrix[row][col])
        seen[row][col] = True

        nextRow = row + dirs[di][0]
        nextCol = col + dirs[di][1]

        if not (0 <= nextRow < m and 0 <= nextCol < n and not seen[nextRow][nextCol]):
            di = (di + 1) % 4

        row = row + dirs[di][0]
        col = col + dirs[di][1]

    return res

## 法二：按层模拟
def spiralOrder1(self, matrix: List[List[int]]) -> List[int]:
    startRow, endRow = 0, len(matrix) - 1
    startCol, endCol = 0, len(matrix[0]) - 1

    res = list()
    while startRow <= endRow and startCol <= endCol:
        # top 行
        for col in range(startCol, endCol + 1): res.append(matrix[startRow][col])
        # right 列
        for row in range(startRow + 1, endRow + 1): res.append(matrix[row][endCol])
        if startRow < endRow and startCol < endCol:
            # bottom 行
            for col in range(endCol - 1, startCol, -1): res.append(matrix[endRow][col])
            # left 列
            for row in range(endRow, startRow, -1): res.append(matrix[row][startCol])
        startRow += 1
        endRow -= 1
        startCol += 1
        endCol -= 1

    return res