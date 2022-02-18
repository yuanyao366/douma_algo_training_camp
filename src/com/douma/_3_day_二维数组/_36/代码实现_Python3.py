from typing import List


def isValidSudoku(self, board: List[List[str]]) -> bool:
    row_used = [[False] * 9 for _ in range(9)]
    col_used = [[False] * 9 for _ in range(9)]
    box_used = [[False] * 9 for _ in range(9)]

    for row in range(len(board)):
        for col in range(len(board[0])):
            if board[row][col] != '.':
                num = ord(board[row][col]) - ord('1')

                if row_used[row][num]:
                    return False
                else:
                    row_used[row][num] = True

                if col_used[col][num]:
                    return False
                else:
                    col_used[col][num] = True

                box_index = row // 3 + (col // 3) * 3
                if box_used[box_index][num]:
                    return False
                else:
                    box_used[box_index][num] = True

    return True
