from typing import List


def getRow(self, row_index: int) -> List[int]:
    one_row = [0] * (row_index + 1)
    one_row[0] = 1
    for row in range(1, row_index + 1):
        for col in range(row, 0, -1):
            one_row[col] += one_row[col - 1]
    return one_row