/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solve = function(board) {
    const dirs = [[-1, 0], [1, 0], [0, -1], [0, 1]]

    const rows = board.length, cols = board[0].length
    const visited = new Array(rows).fill(0).map(() => new Array(cols).fill(false))

    const inArea = (row, col) => {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    const dfs = (row, col) => {
        if (!inArea(row, col) || board[row][col] == 'X' || visited[row][col]) {
            return
        }

        visited[row][col] = true

        for (let dir of dirs) {
            const nextRow = row + dir[0]
            const nextCol = col + dir[1]
            dfs(nextRow, nextCol)
        }
    }

    // 从四周查找 O 字母
        for (let col = 0; col < cols; col++) {
            // 1. 第一行
            if (board[0][col] == 'O' && !visited[0][col]) {
                dfs(0, col);
            }

            // 2. 最后一行
            if (board[rows - 1][col] == 'O' && !visited[rows - 1][col]) {
                dfs(rows - 1, col);
            }
        }

        for (let row = 1; row < rows - 1; row++) {
            // 3. 第一列
            if (board[row][0] == 'O' && !visited[row][0]) {
                dfs(row, 0);
            }

            // 4. 最后一列
            if (board[row][cols - 1] == 'O' && !visited[row][cols - 1]) {
                dfs(row, cols - 1);
            }
        }

        // 将没有被访问过并且等于 O 的字母填充为 X
        for (let row = 1; row < rows - 1; row++) {
            for (let col = 1; col < cols - 1; col++) {
                if (board[row][col] == 'O' && !visited[row][col]) {
                    board[row][col] = 'X';
                }
            }
        }

};