func solveSudoku(board [][]byte)  {
    // 用于标识数字是否在行、列、箱子中使用过
    var rowUsed = [9][10]bool{}
    var colUsed = [9][10]bool{}
    var boxUsed = [3][3][10]bool{}

    // 初始化
    for i := range board {
        for j := range board {
            var num = int(board[i][j] - '0')
            if num >= 1 && num <= 9 {
                rowUsed[i][num] = true
                colUsed[j][num] = true
                boxUsed[i / 3][j / 3][num] = true
            }
        }
    }

    var backTrack func(int, int) bool
    backTrack = func(row int, col int) bool {
        if col == len(board[0]) {
            row++; // 下一行
            col = 0; // 第一列
            if row == len(board) {
                return true
            }
        }

        if board[row][col] == '.' {
            // 尝试填充 1 到 9 数字
            for num := 1; num <= 9; num++ {
                var canPlaced = !(rowUsed[row][num] ||
                            colUsed[col][num] ||
                            boxUsed[row / 3][col / 3][num])
                if canPlaced { // 填充当前的空格
                    rowUsed[row][num] = true
                    colUsed[col][num] = true
                    boxUsed[row / 3][col / 3][num] = true

                    board[row][col] = byte('0' + num)
                    // 尝试填充下一个空格，如果填充成功的话，则返回 true
                    if backTrack(row, col + 1) {
                        return true
                    }

                    // 否则的话，回溯
                    board[row][col] = '.'
                    rowUsed[row][num] = false
                    colUsed[col][num] = false
                    boxUsed[row / 3][col / 3][num] = false
                }
            }
        } else { // 跳过这个空格
            return backTrack(row, col + 1)
        }
        return false
    }

    // bug 修复，需要调用回溯的方法
    backTrack(0, 0)
}
