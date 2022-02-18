var isValidSudoku = function(board) {
    const rowUsed = new Array(9).fill(0).map(() => new Array(9).fill(false))
    const colUsed = new Array(9).fill(0).map(() => new Array(9).fill(false))
    const boxUsed = new Array(9).fill(0).map(() => new Array(9).fill(false))

    for (let row = 0; row < board.length; row++) {
        for (let col = 0; col < board[0].length; col++) {
            if (board[row][col] != '.') {
                const num = board[row][col] - '1'

                if (rowUsed[row][num]) return false
                else rowUsed[row][num] = true

                if (colUsed[col][num]) return false
                else colUsed[col][num] = true

                const bonIndex = Math.floor(row / 3) + Math.floor(col / 3) * 3

                if (boxUsed[bonIndex][num]) return false
                else boxUsed[bonIndex][num] = true
            }
        }
    }

    return true
};