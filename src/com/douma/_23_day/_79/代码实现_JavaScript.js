/**
 * @param {character[][]} board
 * @param {string} word
 * @return {boolean}
 */
var exist = function(board, word) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    const rows = board.length, cols = board[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const dfs = (row, col, index) => {
        if (board[row][col] != word[index]) return false
        else if (index == word.length - 1) return true

        visited[row][col] = true
        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            if (inArea(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                if (dfs(nextRow, nextCol, index + 1)) return true
            }
        }
        visited[row][col] = false
        return false
    }

    for (let row = 0; row < rows; row++) {
        for (let col = 0; col < cols; col++) {
            if (board[row][col] == word[0]) {
                if (dfs(row, col, 0)) return true
            }
        }
    }

    return false
};