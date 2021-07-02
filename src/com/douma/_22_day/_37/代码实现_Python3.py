from typing import List


class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        row_used = [[False] * 10 for _ in range(9)]
        col_used = [[False] * 10 for _ in range(9)]
        box_used = [[[False] * 10 for _a in range(3)] for _b in range(3)]

        for i in range(len(board)):
            for j in range(len(board[0])):
                num = ord(board[i][j]) - ord('0')
                if 1 <= num <= 9:
                    row_used[i][num] = col_used[j][num] = box_used[i // 3][j // 3][num] = True

        def back_track(row, col) -> bool:
            if col == len(board[0]):
                row, col = row + 1, 0
                if row == len(board): return True

            if board[row][col] == '.':
                for num in range(1, 10):
                    can_place = row_used[row][num] == col_used[col][num] == box_used[row // 3][col // 3][num] == False
                    if can_place:
                        row_used[row][num] = col_used[col][num] = box_used[row // 3][col // 3][num] = True

                        board[row][col] = str(num)
                        # 尝试填充下一个空格，如果填充成功的话，则返回 true
                        if back_track(row, col + 1): return True

                        # 否则的话，回溯
                        board[row][col] = '.'
                        row_used[row][num] = col_used[col][num] = box_used[row // 3][col // 3][num] = False
            else:
                return back_track(row, col + 1)
            return False

        back_track(0, 0)