from typing import List


def generate(self, num_rows: int) -> List[List[int]]:
    rows = list()
    for row in range(0, num_rows):
        one_row = list()
        for col in range(0, row + 1):
            if col == 0 or col == row:
                one_row.append(1)
            else:
                pre_row = rows[row - 1]
                one_row.append(pre_row[col - 1] + pre_row[col])
        rows.append(one_row)
    return rows
