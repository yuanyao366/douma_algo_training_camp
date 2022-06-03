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

                """
                boxIndex 是如何推导出来的呢？
                首先，我们要明白一个知识点，那就是二维数组转一维数组
                假设二维数组中有个元素，它的坐标索引是 (row, col)，
                那么它在一维数组中的索引是：index = col + row * n，假设 n 是二维数组的总列数

                对于 boxIndex，首先，我们将二维数组元素 (row, col)，
                转成这个元素所在的 box 的坐标索引为 (row / 3, col / 3)
                然后将二维的 3×3 的 box 数组转成一维的，boxIndex = col / 3 + (row / 3) * 3

                在这里 row 和 col 是可以互换位置的，因为 box 数组是一个 3×3 的二维数组，行数和列数是一样的
                """
                box_index = row // 3 + (col // 3) * 3
                if box_used[box_index][num]:
                    return False
                else:
                    box_used[box_index][num] = True

    return True
