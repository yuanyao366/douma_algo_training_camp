def generate(self, numRows: int) -> List[List[int]]:
    rows = list()
    for row in range(0, numRows):
        oneRow = list()
        for col in range(0, row + 1):
            if col == 0 or col == row:
                oneRow.append(1)
            else:
                preRow = rows[row - 1]
                oneRow.append(preRow[col - 1] + preRow[col])
        rows.append(oneRow)
    return rows