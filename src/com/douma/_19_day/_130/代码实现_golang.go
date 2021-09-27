var dirs = [][]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

func solve(board [][]byte)  {
    var rows, cols = len(board), len(board[0])
    var visited = make([][]bool, rows)
    for i := range visited {
        visited[i] = make([]bool, cols)
    }

    var dfs func(int, int)
    dfs = func(row int, col int) {
        if row < 0 || row >= rows ||
            col < 0 || col >= cols ||
            board[row][col] == 'X' || visited[row][col] {
            return
        }

        visited[row][col] = true

        for _, dir := range dirs {
            var nextRow = row + dir[0]
            var nextCol = col + dir[1]
            dfs(nextRow, nextCol)
        }
    }

    // 从四周查找 O 字母
    for col := 0; col < cols; col++ {
        // 1. 第一行
        if board[0][col] == 'O' && !visited[0][col] {
            dfs(0, col)
        }

        // 2. 最后一行
        if board[rows - 1][col] == 'O' && !visited[rows - 1][col] {
            dfs(rows - 1, col)
        }
    }

    for row := 1; row < rows - 1; row++ {
        // 3. 第一列
        if board[row][0] == 'O' && !visited[row][0] {
            dfs(row, 0)
        }
        // 4. 最后一列
        if board[row][cols - 1] == 'O' && !visited[row][cols - 1] {
            dfs(row, cols - 1)
        }
    }

    // 将没有被访问过并且等于 O 的字母填充为 X
    for row := 1; row < rows - 1; row++ {
        for col := 1; col < cols - 1; col++ {
            if board[row][col] == 'O' && !visited[row][col] {
                board[row][col] = 'X'
            }
        }
    }
}