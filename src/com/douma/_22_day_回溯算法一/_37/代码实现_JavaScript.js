/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solveSudoku = function(board) {
    const rowUsed = new Array(9).fill(0).map(() => new Array(10).fill(false))
    const colUsed = new Array(9).fill(0).map(() => new Array(10).fill(false))
    const boxUsed = new Array(3).fill(0).map(() => new Array(3).fill(0).map(() => new Array(10).fill(false)))

    // 初始化
    for (let i = 0; i < board.length; i++) {
        for (let j = 0; j < board.length; j++) {
            const num = board[i][j].charCodeAt() - '0'.charCodeAt();
            if (num >= 1 && num <= 9) {
                rowUsed[i][num] = true
                colUsed[j][num] = true
                boxUsed[Math.floor(i / 3)][Math.floor(j / 3)][num] = true
            }
        }
    }

    const backTrack = (row, col) => {
        if (col == board[0].length) {
            // 下一行
            row++
            // 第一列
            col = 0
            if (row == board.length) {
                return true
            }
        }

        if (board[row][col] == '.') {
            // 尝试填充 1 到 9 数字
            for (let num = 1; num <= 9; num++) {
                const canPlaced = !(rowUsed[row][num]
                        || colUsed[col][num]
                        || boxUsed[Math.floor(row / 3)][Math.floor(col / 3)][num])
                if (canPlaced) { // 填充当前的空格
                    rowUsed[row][num] = true
                    colUsed[col][num] = true
                    boxUsed[Math.floor(row / 3)][Math.floor(col / 3)][num] = true

                    board[row][col] = '' + num
                    // 尝试填充下一个空格，如果填充成功的话，则返回 true
                    if (backTrack(row, col + 1)) {
                        return true
                    }

                    // 否则的话，回溯
                    board[row][col] = '.'
                    rowUsed[row][num] = false
                    colUsed[col][num] = false
                    boxUsed[Math.floor(row / 3)][Math.floor(col / 3)][num] = false
                }
            }
        } else { // 跳过这个空格
            return backTrack(row, col + 1)
        }

        return false
    }

    backTrack(0, 0)
};