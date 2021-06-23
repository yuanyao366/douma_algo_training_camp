/**
 * @param {character[][]} board
 * @param {number[]} click
 * @return {character[][]}
 */
var updateBoard = function(board, click) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1, 1]]

    const rows = board.length, cols = board[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const dfs = (row, col) => {
        if (!inArea(row, col) || board[row][col] != 'E' || visited[row][col]) {
            return
        }

        visited[row][col] = true
        let mines = 0
        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            if (inArea(nextRow, nextCol) && board[nextRow][nextCol] == 'M') {
                mines++
            }
        }

        if (mines > 0) {
            board[row][col] = mines + ''
        } else {
            board[row][col] = 'B'
            for (let dir of dirs) {
                const nextRow = row + dir[0]
                const nextCol = col + dir[1]
                dfs(nextRow, nextCol)
            }
        }
    }

    if (board[click[0]][click[1]] == 'M') {
        board[click[0]][click[1]] = 'X'
    } else {
        dfs(click[0], click[1])
    }

    return board
};