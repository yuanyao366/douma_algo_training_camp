def getRow(self, rowIndex: int) -> List[int]:
    oneRow = [0] * (rowIndex + 1)
    oneRow[0] = 1
    for row in range(1, rowIndex + 1):
        for col in range(row, 0, -1):
            oneRow[col] += oneRow[col - 1]
    return oneRow