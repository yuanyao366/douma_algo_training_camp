def isValidSudoku(self, board: List[List[str]]) -> bool:
    rowUsed = [[False] * 9 for _ in range(9)]
    colUsed = [[False] * 9 for _ in range(9)]
    boxUsed = [[False] * 9 for _ in range(9)]

    for row in range(len(board)):
        for col in range(len(board[0])):
            if board[row][col] != '.':
                num = ord(board[row][col]) - ord('1')

                if rowUsed[row][num]: return False
                else: rowUsed[row][num] = True

                if colUsed[col][num]: return False
                else: colUsed[col][num] = True

                boxIndex = row // 3 + (col // 3) * 3
                if boxUsed[boxIndex][num]: return False
                else: boxUsed[boxIndex][num] = True

    return True